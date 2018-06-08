package tellhow.cavate.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import tellhow.cavate.pojo.Department;
import tellhow.cavate.pojo.Leave;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.utils.DateUtil;
import tellhow.cavate.utils.PageResult;

@Controller
@RequestMapping("/leave")
public class LeaveController extends BaseController{

	    //1.跳转到添加请假页面
	 	@RequestMapping(value = "addUI")
	 	public ModelAndView addUI() {
            //查询当前用户的下一级审批人
	 		String zw= (String) session.getAttribute("sessionZW");//获取当前用户的职务
			String uid= (String) session.getAttribute("sessionId");//获取当前用户id
			String did= (String) session.getAttribute("sessionDid");//所在部门id
			String jh= (String) session.getAttribute("sessionJH");//所在部门id
			//科级及以下,交给分管副处长
			List<Users> sprList=new ArrayList<Users>();
			
			//如果是纪委、宣教办、专委会走特定审批流程
			if(did.equals("jw")){
				//如果是纪委 龚平秋,推荐政监处
				if(jh.equals("000257")){
					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
					map.put("xyspr", "政工监察处");
				}else{
					Users users=usersService.getByJH("000257");
					sprList.add(users);
					map.put("xyspr", "所在处主要负责人");
				}
			}else if(did.equals("xjb")){
				//如果是宣教办刘正和,推荐政监处
				if(jh.equals("000996")){
					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
					map.put("xyspr", "政工监察处");
				}else{
					Users users=usersService.getByJH("000996");
					sprList.add(users);
					map.put("xyspr", "所在处主要负责人");
				}
			}else if(did.equals("zwh")){
				//如果是专委会石则全,推荐政监处
				if(jh.equals("003017")){
					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
					map.put("xyspr", "政工监察处");
				}else{System.out.println("3333=====");
					Users users=usersService.getByJH("003017");
					sprList.add(users);
					map.put("xyspr", "所在处主要负责人");
				}
			}else{//其他部门走一般流程
				//科级及以下,交给分管副处长
				if(zw.equals("01")){
					sprList=usersService.getSprList(did,"02");//获取本部门的副处
					map.put("xyspr", "分管副处长");
				}
				//副处、副调研员,交给处长
				if(zw.equals("02")){
					sprList=usersService.getSprList(did,"03");//获取本部门的处长
					map.put("xyspr", "所在处主要负责人");
				}
				//处长(政委)、调研员、局领导  -- 交给政监处
				if(zw.equals("03")||zw.equals("07")||zw.equals("08")){
					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
					map.put("xyspr", "政工监察处");
				}
				//高警总队直属支队副调研员交给支队领导
				if(zw.equals("04")){
					sprList=usersService.getSprList(did,"05");//获取本支队领导
					map.put("xyspr", "支队主要负责人");
				}
				//支队领导交给总队领导
				if(zw.equals("05")){
					sprList=usersService.getGjSprList("360000000510");//获取高警总队领导
					map.put("xyspr", "高警总队主要负责人");
				}	
			}
			
			//总队领导请假和部门正处副处一样
	 		map.put("sprList", sprList);
	 		//获取当前用户有多少没有销假的请假单
	 		int count=leaveService.getDXJCount(uid);
	 		map.put("count", count);
	 		return new ModelAndView("/leaveJsp/addUI",map);
	 	}
	 	
	 	//2.添加请假单
	 	@RequestMapping(value = "addLeave")
	 	@ResponseBody
	 	public String addLeave(Leave leave){
	 		String  result=leaveService.addLeave(leave);
	 		if(result.equals("success")){
	 			return "success";
	 		}else if(result.equals("nxjtsbz")){
	 			return "nxjtsbz";
	 		} 
	 		else if(result.equals("nxjtsgd")){
	 			return "nxjtsgd";
	 		} 
	 		return "error";
	 	}
	 	
	 	//3.我的请假单列表
	 	@RequestMapping(value="/mylist")
		public ModelAndView mylist(){
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getMyList(currentPage);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
	 		return new ModelAndView("/leaveJsp/mylist", map);
	 	}
	 	
	 	

	 	//4.需要销假的请假单 ,只有审批通过才可以销假
	 	@RequestMapping(value="/myXjList")
		public ModelAndView myXjList(){
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getMyXjList(currentPage);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
	 		return new ModelAndView("/leaveJsp/myXjList", map);
	 	}
	 	
	 	//5.销假页面xjUI
	 	@RequestMapping(value = "xjUI")
	 	public ModelAndView xjUI() {
            //获取请假单的详细信息
	 		String id=request.getParameter("id");
	 		Leave leave=leaveService.getById(id);
	 		if(leave!=null){
	 			//获取请假结束日期和当前日期间隔天数
	 			String nowdate=DateUtil.ChangeTime2(new Date());
	 			String qjjsrq=DateUtil.ChangeTime2(leave.getEndDate());
	 			//2018-05-24====2018-05-23====-1
	 			long count=DateUtil.getDaySub(qjjsrq, nowdate);
	 			//正常销假
	 			if(count>=0&&count<=1){
	 				map.put("xjzt", "01");
	 			}
	 			//延期
	 			if(count>1){
	 				map.put("xjzt", "02");
	 			}
	 			//提前
                if(count<0){
                	map.put("xjzt", "03");
	 			}
	 			map.put("count", Math.abs(count));
	 			map.put("leave", leave);
	 		}
	 		return new ModelAndView("/leaveJsp/xjUI",map);
	 	}
	 	
	 	//6.销假
	 	@RequestMapping(value = "xj")
	 	@ResponseBody
	 	public String xj(Leave leave){
	 		if(leave.getXjzt().equals("正常")){
	 			leave.setXjzt("01");
	 		}
	 		if(leave.getXjzt().equals("延期")){
	 			leave.setXjzt("02");
	 		}
	 		if(leave.getXjzt().equals("提前")){
	 			leave.setXjzt("03");
	 		}
	 		leave.setXjsj(new Date());
	 		boolean result=leaveService.updateXJ(leave);
	 		if(result){
	 			return "success";
	 		}
	 		return "error";
	 	}
	 	
	 	//7.销假详情xjdetail
	 	@RequestMapping(value = "xjdetail")
	 	public ModelAndView xjdetail() {
            //获取请假单的详细信息
	 		String id=request.getParameter("id");
	 		Leave leave=leaveService.getById(id);
	 		map.put("leave", leave);
	 		return new ModelAndView("/leaveJsp/xjdetail",map);
	 	}
	 	
	 	
	 	//8.删除请假单deleteLeave
	 	@RequestMapping(value = "deleteLeave")
	 	@ResponseBody
	 	public String deleteLeave(){
	 		String id=request.getParameter("id");
	 		boolean result=leaveService.deleteById(id);
	 		if(result){
	 			return "success";
	 		}
	 		return "error";
	 	}
	 	
	 	//9.请假详情details
	 	@RequestMapping(value = "details")
	 	public ModelAndView details() {
            //获取请假单的详细信息
	 		String id=request.getParameter("id");
	 		Leave leave=leaveService.getById(id);
	 		if(leave!=null){
	 			Users user=usersService.getUserById(leave.getCreateUser());
	 			if(user.getHF().equals("0")){
	 				map.put("HF", "未婚");
	 			}else{
	 				map.put("HF", "已婚");
	 			}
	 			map.put("user", user);
	 			
	 			//哪年哪月哪日
	 			String startDate=DateUtil.ChangeTime2(leave.getStartDate());//2018-04-05
	 			String endDate=DateUtil.ChangeTime2(leave.getEndDate());
	 			map.put("startYear", startDate.substring(0, 4));
	 			map.put("startMonth", startDate.substring(5, 7));
	 			map.put("startDay", startDate.substring(8, 10));
	 			map.put("endYear", endDate.substring(0, 4));
	 			map.put("endMonth",endDate.substring(5, 7));
	 			map.put("endDay", endDate.substring(8, 10));
	 			
	 			//审批意见
 				if(leave.getDysprid()!=null){
 					map.put("dysprid", usersService.getUserById(leave.getDysprid()).getZSXM());
 					map.put("dyspryj", leave.getDyspryj());
 					map.put("dysprsj", leave.getDysprsj());
 				}else{
 					map.put("dysprid","");
 					map.put("dyspryj", "");
 					map.put("dysprsj", "");
 				}
 				if(leave.getDesprid()!=null){
 					map.put("desprid", usersService.getUserById(leave.getDesprid()).getZSXM());
 					map.put("despryj", leave.getDespryj());
 					map.put("desprsj", leave.getDesprsj());
 				}else{
 					map.put("desprid","");
 					map.put("despryj","");
 					map.put("desprsj", "");
 				}
 				if(leave.getDssprid()!=null){
 					map.put("dssprid", usersService.getUserById(leave.getDssprid()).getZSXM());
 					map.put("dsspryj", leave.getDsspryj());
 					map.put("dssprsj", leave.getDssprsj());
 				}else{
 					map.put("dssprid", "");
 					map.put("dsspryj", "");
 					map.put("dssprsj", "");
 				}
 				if(leave.getDsisprid()!=null){
 					map.put("dsisprid", usersService.getUserById(leave.getDsisprid()).getZSXM());
 					map.put("dsispryj", leave.getDsispryj());
 					map.put("dsisprsj", leave.getDsisprsj());
 				}else{
 					map.put("dsisprid", "");
 					map.put("dsispryj", "");
 					map.put("dsisprsj", "");
 				}
	 			//如果是专委会、纪委、宣教办则另一个页面显示
 				if(user.getDid().equals("jw")||user.getDid().equals("xjb")||user.getDid().equals("zwh")){
 					map.put("leave", leave);
 			 		return new ModelAndView("/leaveJsp/details2",map);
 				}else{
 					map.put("leave", leave);
 			 		return new ModelAndView("/leaveJsp/details",map);
 				}
 				
	 		}else{
	 			map.put("leave", leave);
		 		return new ModelAndView("/leaveJsp/details",map);
	 		}

	 	}
	 	
	 	//10.系统请假单xtList
	 	@RequestMapping(value="/xtList")
		public ModelAndView xtList(){
	 		//获取查询添加
	 		String ZSXM=request.getParameter("ZSXM");
	 		String pId2=request.getParameter("pId2");//部门名称
	 		String did=request.getParameter("did");//部门编号
	 		String spState=request.getParameter("spState");//部门编号
	 		//保存查询条件
	 		request.setAttribute("ZSXM", ZSXM);
	 		request.setAttribute("pId2", pId2);
	 		request.setAttribute("did", did);
	 		request.setAttribute("spState", spState);
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getXtList(currentPage,ZSXM,did,spState);
	 		List<Leave> list=result.getList();
	 		 //获取用户信息
	        List<Users> qjuserlist=new ArrayList<Users>();
			for(int i=0;i<list.size();i++){
				qjuserlist.add(usersService.getUserById(list.get(i).getCreateUser()));
			}
	 		
	 		map.put("qjuserlist", qjuserlist);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
			//交管局下所有部门
			toJsonString("360000000000");
	 		return new ModelAndView("/leaveJsp/xtList", map);
	 	}
	 	
	 	
	 	//11.系统销假请假单xtxjList
	 	@RequestMapping(value="/xtxjList")
		public ModelAndView xtxjList(){
	 		//获取查询添加
	 		String ZSXM=request.getParameter("ZSXM");
	 		String pId2=request.getParameter("pId2");//部门名称
	 		String did=request.getParameter("did");//部门编号
	 		String sfxj=request.getParameter("sfxj");//是否销假
	 		//保存查询条件
	 		request.setAttribute("ZSXM", ZSXM);
	 		request.setAttribute("pId2", pId2);
	 		request.setAttribute("did", did);
	 		request.setAttribute("sfxj", sfxj);
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getXtxjList(currentPage,ZSXM,did,sfxj);
	 		List<Leave> list=result.getList();
	 		 //获取用户信息
	        List<Users> qjuserlist=new ArrayList<Users>();
			for(int i=0;i<list.size();i++){
				qjuserlist.add(usersService.getUserById(list.get(i).getCreateUser()));
			}
	 		
	 		map.put("qjuserlist", qjuserlist);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
			//交管局下所有部门
			toJsonString("360000000000");
	 		return new ModelAndView("/leaveJsp/xtxjList", map);
	 	}
	 	
	 	
	 	//11.部门请假单bmList
	 	@RequestMapping(value="/bmList")
		public ModelAndView bmList(){
	 		//获取查询添加
	 		String ZSXM=request.getParameter("ZSXM");
	 		String did= (String) session.getAttribute("sessionDid");//所在部门id
	 		String spState=request.getParameter("spState");//审批状态
	 		//保存查询条件
	 		request.setAttribute("ZSXM", ZSXM);
	 		request.setAttribute("did", did);
	 		request.setAttribute("spState", spState);
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getXtList(currentPage,ZSXM,did,spState);
	 		List<Leave> list=result.getList();
	 		 //获取用户信息
	        List<Users> qjuserlist=new ArrayList<Users>();
			for(int i=0;i<list.size();i++){
				qjuserlist.add(usersService.getUserById(list.get(i).getCreateUser()));
			}
	 		
	 		map.put("qjuserlist", qjuserlist);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
	 		return new ModelAndView("/leaveJsp/bmList", map);
	 	}
	 	
	 	
	 	//12.部门销假请假单bmxjList
	 	@RequestMapping(value="/bmxjList")
		public ModelAndView bmxjList(){
	 		//获取查询添加
	 		String ZSXM=request.getParameter("ZSXM");
	 		String did= (String) session.getAttribute("sessionDid");//所在部门id
	 		String sfxj=request.getParameter("sfxj");//是否销假
	 		//保存查询条件
	 		request.setAttribute("ZSXM", ZSXM);
	 		request.setAttribute("did", did);
	 		request.setAttribute("sfxj", sfxj);
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getXtxjList(currentPage,ZSXM,did,sfxj);
	 		List<Leave> list=result.getList();
	 		 //获取用户信息
	        List<Users> qjuserlist=new ArrayList<Users>();
			for(int i=0;i<list.size();i++){
				qjuserlist.add(usersService.getUserById(list.get(i).getCreateUser()));
			}
	 		
	 		map.put("qjuserlist", qjuserlist);
	 		map.put("pageResult", result);
	 		request.getSession().setAttribute("totalPage", result.getPage().getTotalPage());
			request.getSession().setAttribute("currentPage", result.getPage().getCurrentPage());
	 		return new ModelAndView("/leaveJsp/bmxjList", map);
	 	}
	 	
	 	
	 	//13.销假审批流程图
	 	@RequestMapping(value = "splcUI")
	 	public ModelAndView splcUI() {
	 		return new ModelAndView("/leaveJsp/splcUI");
	 	}
	 	
	 	
	 	// 工具类,转jsonString
	 	public void toJsonString(String BMBH) {
	 		Department myDepartment = departmentService.getDepartmentById(BMBH);
	 		List<Department> suborList = departmentService.getAllXJDepartments(BMBH);
	 		List<Department> allDept = new ArrayList<Department>();
	 		for (Department dept : suborList) {
	 			if (dept != null) {
	 				if (dept.getSJBM() == null) {
	 					dept.setSJBM("0");
	 				}
	 				allDept.add(dept);
	 			}
	 		}
	 		String jsonString = JSON.toJSONString(allDept);
	 		request.setAttribute("jsonString", jsonString);
	 		if (myDepartment != null) {
	 			String leftpId = myDepartment.getSJBM() == null ? "0" : myDepartment.getSJBM();
	 			request.setAttribute("pid", leftpId);
	 		}
	 	}
}
