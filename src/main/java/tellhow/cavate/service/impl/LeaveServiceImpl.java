package tellhow.cavate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;



import tellhow.cavate.pojo.Holiday;
import tellhow.cavate.pojo.Leave;
import tellhow.cavate.pojo.ShenPi;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.service.LeaveService;
import tellhow.cavate.utils.DateUtil;
import tellhow.cavate.utils.Page;
import tellhow.cavate.utils.PageResult;
import tellhow.cavate.utils.PageUtil;
import tellhow.cavate.utils.UuidUtil;

@Service
public class LeaveServiceImpl extends BaseService implements LeaveService{

	//1.添加请假单
	@Override
	public String addLeave(Leave leave) {
		//获取用户id
		String uid= (String) session.getAttribute("sessionId");
		//获取用户信息
		Users user=usersMapper.getUserById(uid);
		//获取请假开始日期和结束日期之间的实际工作日天数workDays[0]和法定节假日天数workDays[1]
		int [] workDays=workDays(leave.getStartDate(),leave.getEndDate());
		if(user!=null){
			if(leave.getLeaveType().endsWith("年休假")){
				if(workDays[0]>user.getNXJTS()){
					return "nxjtsbz";//可休年休假天数不足
				}else{
					//跟新年休假
					user.setNXJTS(user.getNXJTS()-workDays[0]);
					usersMapper.updateNXJTS(user);
				}
			}else{
				if(workDays[0]<leave.getNxjts()){
					return "nxjtsgd";//包含的年休假不能大于请假天数
				}
				if(leave.getNxjts()>0){
					//跟新年休假
					user.setNXJTS(user.getNXJTS()-leave.getNxjts());
					usersMapper.updateNXJTS(user);
				}
				//不是年休假
				if(leave.getNxjts()>user.getNXJTS()){
				    return "nxjtsbz";//可休年休假天数不足
			    }
			}
			//添加请假信息
			leave.setId(UuidUtil.get32UUID());
			leave.setCreateTime(new Date());
			leave.setCreateUser(uid);
			leave.setDays(workDays[0]);//实际请假天数
			leave.setOtherDays(workDays[1]);//包含的双休法定的天数
			leave.setSpState("00");//待审批
			leave.setSfxj("00");//未销假
			boolean result=leaveMapper.addLeave(leave); 
			if(result){
				return "success";
			}else{
				return "error";
			}
		}else{
			return "error";//用户为空
		}
	}

	
	//2.我的请假单
	@Override
	public PageResult getMyList(String currentPage) {
		//处理页码
		int nowPage = 0;
		if (currentPage != null) {
			nowPage = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(nowPage);
		//获取当前用户id
		String uid= (String) session.getAttribute("sessionId");
		param.put("createUser", uid);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),leaveMapper.getMyListCount(param));
		//封装条件
		param.put("page", page);
		List<Leave> list=leaveMapper.getMyList(param);
		PageResult pageResult = new PageResult(page, list);
		return pageResult;
	}
	
	
	//3.根据id获取请假单信息
	@Override
	public Leave getById(String id) {
		return leaveMapper.getById(id);
	}
	
	
	//4.计算请假开始日期和结束日期中包含的工作日（扣除节假日，周末）和周末节假日
	public int[] workDays(Date dateStart,Date dateEnd){
		int[] work=new int[2];//work[0]实际工作日  work[1]周末和法定节假日
		List<Holiday> ht = holidayMapper.getHolidays();//获取存入数据库的节假日对象
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//统一集合中的格式
		List<String> date1 = new ArrayList<String>();//存新格式日期
		for (int i = 0; i < ht.size(); i++) {//循环遍历对象
			date1.add(i, sdf.format(ht.get(i).getHolidayDate()));//获取所需属性 转格式存入新集合
		}
		//页面上获取时间区间所有日期集合
		List<Date> listDate = DateUtil.getBetweenDates(dateStart, dateEnd);
		List<String> date2 = new ArrayList<String>();//存新格式日期
		for (int i = 0; i < listDate.size(); i++) {//循环遍历日期集合
			date2.add(i, sdf.format(listDate.get(i)));//获取所需属性 转格式存入新集合
		}
		List<String> dateNo = new ArrayList<String>(date2);//构建date2的副本
		//请假总天数
		int alldays=dateNo.size();
		dateNo.removeAll(date1);// 去除相同元素
		//实际工作日
		work[0]=dateNo.size();
		//节假日、周末
		work[1]=alldays-work[0];
		return work;
	}


	//5,我的审批记录
	@Override
	public PageResult getMySphistory(String uid, String zSXM, String did, String currentPage) {
		//处理页码
		int nowPage = 0;
		if (currentPage != null) {
			nowPage = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(nowPage);
		param.put("uid", uid);
		param.put("ZSXM", zSXM);
		param.put("did", did);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),leaveMapper.getMySphistoryCount(param));
		//封装条件
		param.put("page", page);
		List<Leave> list=leaveMapper.getMySphistory(param);
		PageResult pageResult = new PageResult(page, list);
		return pageResult;
	}


	//6.需要销假的请假单 ,只有审批通过才可以销假
	@Override
	public PageResult getMyXjList(String currentPage) {
		//处理页码
		int nowPage = 0;
		if (currentPage != null) {
			nowPage = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(nowPage);
		//获取当前用户id
		String uid= (String) session.getAttribute("sessionId");
		param.put("createUser", uid);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),leaveMapper.getMyXjListCount(param));
		//封装条件
		param.put("page", page);
		List<Leave> list=leaveMapper.getMyXjList(param);
		PageResult pageResult = new PageResult(page, list);
		return pageResult;
	}

    //7.跟新销假
	@Override
	public boolean updateXJ(Leave leave) {
		return leaveMapper.updateXJ(leave);
	}


	//8.获取当前用户有多少待销假的请假单
	@Override
	public int getDXJCount(String uid) {
		return leaveMapper.getDXJCount(uid);
	}


	//9.删除请假单
	@Override
	public boolean deleteById(String id) {
		return leaveMapper.deleteById(id);
	}


	//10.系统请假单列表
	@Override
	public PageResult getXtList(String currentPage,String zSXM, String did, String spState) {
		//处理页码
		int nowPage = 0;
		if (currentPage != null) {
			nowPage = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(nowPage);
		//封装查询条件
		param.put("ZSXM", zSXM);
		param.put("did", did);
		param.put("spState", spState);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),leaveMapper.getXtListCount(param));
		//封装条件
		param.put("page", page);
		List<Leave> list=leaveMapper.getXtList(param);
		PageResult pageResult = new PageResult(page, list);
		return pageResult;
	}

    //11.系统销假单列表
	@Override
	public PageResult getXtxjList(String currentPage,String zSXM, String did, String sfxj) {
		//处理页码
		int nowPage = 0;
		if (currentPage != null) {
			nowPage = Integer.parseInt(currentPage);
		}
		Page page = new Page();
		page.setCurrentPage(nowPage);
		//封装查询条件
		param.put("ZSXM", zSXM);
		param.put("did", did);
		param.put("sfxj", sfxj);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),leaveMapper.getXtxjListCount(param));
		//封装条件
		param.put("page", page);
		List<Leave> list=leaveMapper.getXtxjList(param);
		PageResult pageResult = new PageResult(page, list);
		return pageResult;
	}


	



	
	
	
}
