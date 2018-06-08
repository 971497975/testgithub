package tellhow.cavate.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import tellhow.cavate.pojo.Department;
import tellhow.cavate.pojo.Leave;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.utils.PageResult;
import tellhow.cavate.utils.StringUtil;
@Controller
@RequestMapping("/shenpi")
public class ShenPiController extends BaseController{

	    //1.待我审批列表
		@RequestMapping(value="/splist")
		public ModelAndView splist(){
			//获取用户id
			String uid= (String) session.getAttribute("sessionId");
			//获取待我审批的请假单
	        List<Leave> list=spService.getMySpList(uid);
		    //获取
	        List<Users> qjuserlist=new ArrayList<Users>();
			for(int i=0;i<list.size();i++){
				qjuserlist.add(usersService.getUserById(list.get(i).getCreateUser()));
			}
			map.put("qjuserlist", qjuserlist);
			map.put("list", list);
	 		return new ModelAndView("/shenpiJsp/splist", map);
	 	}
		
		//2.审批页面
		@RequestMapping(value = "spUI")
	 	public ModelAndView spUI() {
	 		String id=request.getParameter("id");
	 		//获取该请假单的信息,计算出下一审批人
	 		Leave leave=leaveService.getById(id);
	 		List<Users> sprList=new ArrayList<Users>();
	 		if(leave!=null){
	 			//获取该请假单人的信息
	 			Users users=usersService.getUserById(leave.getCreateUser());
	 			
	 			if(users.getDid().equals("jw")){
	 				//龚平秋
	 				if(users.getJH().equals("000257")){
	 					//第一审批人为政监处,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}else{
	 					//第一审批人为处长,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}
	 			}else if(users.getDid().equals("xjb")){
	 				//刘正和
	 				if(users.getJH().equals("000996")){
	 					//第一审批人为政监处,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}else{
	 					//第一审批人为处长,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}
	 			}
                else if(users.getDid().equals("zwh")){
                	//石则全
	 				if(users.getJH().equals("003017")){
	 					//第一审批人为政监处,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}else{
	 					//第一审批人为处长,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
	 				}
	 			}else{//其他部门正常流程
		 			//科级及以下
		 			if(users.getZW().equals("01")){
		 				//第一审批人为副处,已确定,只需判断第二,第三,第四
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐处级
		 					sprList=usersService.getSprList(users.getDid(),"03");//获取本部门的处长
		 					map.put("xyspr", "所在处主要负责人");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐政监处审批
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())&&StringUtil.isEmpty(leave.getDsisprid())){
		 					//有第一第二第三,没有第四,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDsisprid())){
		 					//有第一第二第三第四,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");//用来显示审批时是否有下一审批人,到这步就不需要显示
		 				}
		 			}
		 			//副处、副调研员
		 			if(users.getZW().equals("02")){
		 				//第一审批人为处长,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
		 			}
		 			
		 			//处长(政委)、调研员、局领导
		 			if(users.getZW().equals("03")){
		 				//第一审批人为政监处,已确定,只需判断第二,第三
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())){
		 					//有第一第二第三,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
		 			}
		 			
		 			//局领导
		 			if(users.getZW().equals("07")||users.getZW().equals("08")){
		 				//第一审批人为政监处,已确定,只需判断第二
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())){
		 					//有第一第二,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
		 			}
		 			
		 			//高警总队直属支队副调研员
		 			if(users.getZW().equals("04")){
		 				//第一审批人为支队主要负责人,已确定,只需判断第二,第三,第四
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐高警总队主要负责人
		 					sprList=usersService.getGjSprList("360000000510");//获取高警总队领导
		 					map.put("xyspr", "高警总队主要负责人");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())&&StringUtil.isEmpty(leave.getDsisprid())){
		 					//有第一第二第三,没有第四,推荐分管局领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDsisprid())){
		 					//有第一第二第三第四,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
		 			}
		 			
		 			//支队领导
		 			if(users.getZW().equals("05")){
		 				//第一审批人为高警总队负责人,已确定,只需判断第二,第三,第四
		 				if(StringUtil.isEmpty(leave.getDesprid())){
		 					//没有第二审批人,推荐政监处
		 					sprList=usersService.getZjSprList("360000000600");//获取政监处审批人信息,副处和正处
		 					map.put("xyspr", "政工监察处");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDesprid())&&StringUtil.isEmpty(leave.getDssprid())){
		 					//有第一第二,没有第三,推荐局分管领导
		 					sprList=usersService.getFgSprList(users.getDid());//获取分管该部门的分管局领导
		 					map.put("xyspr", "局分管领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDssprid())&&StringUtil.isEmpty(leave.getDsisprid())){
		 					//有第一第二第三,没有第四,推荐局主要领导
		 					sprList=usersService.getZySprList();//获取局主要领导
		 					map.put("xyspr", "局主要领导");
		 					map.put("xs", "");
		 				}
		 				if(StringUtil.isNotEmpty(leave.getDsisprid())){
		 					//有第一第二第三第四,不推荐,没有下一审批人下拉框
		 					map.put("xs", "00");
		 				}
		 			}
		 		}
	 		}
	 		map.put("sprList", sprList);
	 		map.put("id", id);
	 		return new ModelAndView("/shenpiJsp/spUI",map);
	 	}
		
		
		//3.同意审批,有下一个审批人,没下一个审批人就结束流程
		@RequestMapping(value = "tysp")
	 	@ResponseBody
	 	public String tysp(){
	 		String id=request.getParameter("id");//请假单id
	 		String spyj=request.getParameter("spyj");//审批意见
	 		String dysprid=request.getParameter("dysprid");//下一审批人id
	 		//获取该请假单的信息
	 		Leave leave=leaveService.getById(id);
	 		//当前用户是第几个审批人
	 		if(leave!=null){
	 			//获取该请假单人的信息
	 			Users users=usersService.getUserById(leave.getCreateUser());
	 			//如果第一审批人结果为空,说明当前用户就是第一审批人
	 			if(StringUtil.isEmpty(leave.getDysprjg())){
	 				leave.setDysprjg("01");//审批结果同意
	 				leave.setDyspryj(spyj);//第一人审批意见
	 				leave.setDysprsj(new Date());//第一审批时间
	 				leave.setDesprid(dysprid);//第二审批人id
	 				leave.setSpState("01");//请假单的最终状态由待审批变为审批中
	 			}
	 			//如果第一审批人结果不为空,第二审批人为空，说明当前用户就是第二审批人
	 			else if(StringUtil.isNotEmpty(leave.getDysprjg())&&StringUtil.isEmpty(leave.getDesprjg())){
	 				leave.setDesprjg("01");//审批结果同意
	 				leave.setDespryj(spyj);//第二人审批意见
	 				leave.setDesprsj(new Date());//第二审批时间
	 				leave.setDssprid(dysprid);//第三审批人id
	 				//如果请假用户是局领导,那么到第二审批人就要结束流程
	 				if(users.getZW().equals("07")||users.getZW().equals("08")){
	 					leave.setSpState("02");
	 				}else{
	 					leave.setSpState("01");//请假单的最终状态由待审批变为审批中
	 				}
	 			}
	 			//如果第二审批人结果不为空,第三审批人为空，说明当前用户就是第三审批人
	 			else if(StringUtil.isNotEmpty(leave.getDesprjg())&&StringUtil.isEmpty(leave.getDssprjg())){
	 				leave.setDssprjg("01");//审批结果同意
	 				leave.setDsspryj(spyj);//第三人审批意见
	 				leave.setDssprsj(new Date());//第三审批时间
	 				leave.setDsisprid(dysprid);//第四审批人id
	 				//如果请假用户是副处、处级,那么到第三审批人就要结束流程
	 				if(users.getZW().equals("02")||users.getZW().equals("03")
	 				   ||users.getDid().equals("jw")||users.getDid().equals("xjb")||users.getDid().equals("zwh")){
	 					leave.setSpState("02");
	 				}else{
	 					leave.setSpState("01");//请假单的最终状态由待审批变为审批中
	 				}
	 			}
	 			//如果第三审批人结果不为空,第四审批人为空，说明当前用户就是第四审批人
	 			else{//(StringUtil.isNotEmpty(leave.getDssprjg())&&StringUtil.isEmpty(leave.getDsisprjg())){
	 				leave.setDsisprjg("01");//审批结果同意
	 				leave.setDsispryj(spyj);//第四人审批意见
	 				leave.setDsisprsj(new Date());//第四审批时间
	 				leave.setSpState("02");//请假单的最终状态为审批通过
	 			}
	 			boolean result=spService.updateLeaveById(leave);//跟新请假单审批信息
	 			if(result){
	 				return "success";
	 			}
	 			return "error";
	 		}
	 		else{
	 			return "error";
	 		}
	 	}
		    
		
		//4.不同意审批
		@RequestMapping(value = "btysp")
	 	@ResponseBody
	 	public String btysp(){
	 		String id=request.getParameter("id");//请假单id
	 		String spyj=request.getParameter("spyj");//审批意见
	 		//获取该请假单的信息
	 		Leave leave=leaveService.getById(id);
	 		//当前用户是第几个审批人
	 		if(leave!=null){
	 			//获取该请假单人的信息
	 			Users users=usersService.getUserById(leave.getCreateUser());
	 			//如果第一审批人结果为空,说明当前用户就是第一审批人
	 			if(StringUtil.isEmpty(leave.getDysprjg())){
	 				leave.setDysprjg("02");//审批结果不同意
	 				leave.setDyspryj(spyj);//第一人审批意见
	 				leave.setDysprsj(new Date());//第一审批时间
	 				leave.setSpState("03");//请假单的最终状态为不通过
	 			}
	 			//如果第一审批人结果不为空,第二审批人为空，说明当前用户就是第二审批人
	 			else if(StringUtil.isNotEmpty(leave.getDysprjg())&&StringUtil.isEmpty(leave.getDesprjg())){
	 				leave.setDesprjg("02");//审批结果不同意
	 				leave.setDespryj(spyj);//第二人审批意见
	 				leave.setDesprsj(new Date());//第二审批时间
	 				leave.setSpState("03");//请假单的最终状态为不通过
	 			}
	 			//如果第二审批人结果不为空,第三审批人为空，说明当前用户就是第三审批人
	 			else if(StringUtil.isNotEmpty(leave.getDesprjg())&&StringUtil.isEmpty(leave.getDssprjg())){
	 				leave.setDssprjg("02");//审批结果不同意
	 				leave.setDsspryj(spyj);//第三人审批意见
	 				leave.setDssprsj(new Date());//第三审批时间
	 				leave.setSpState("03");//请假单的最终状态为不通过
	 			}
	 			//如果第三审批人结果不为空,第四审批人为空，说明当前用户就是第四审批人
	 			else{//(StringUtil.isNotEmpty(leave.getDssprjg())&&StringUtil.isEmpty(leave.getDsisprjg())){
	 				leave.setDsisprjg("02");//审批结果不同意
	 				leave.setDsispryj(spyj);//第四人审批意见
	 				leave.setDsisprsj(new Date());//第四审批时间
	 				leave.setSpState("03");//请假单的最终状态为审批不通过
	 			}
	 			boolean result=spService.updateLeaveById(leave);//跟新请假单审批信息
	 			if(result){
	 				int count=0;
	 				//不同意要归还年休假天数
	 				if(leave.getLeaveType().equals("年休假")){
	 					count=leave.getDays();
	 				}else{
	 					count=leave.getNxjts();
	 				}
	 				int nxjts=users.getNXJTS()+count;//原年休假天数就等于休假中包含的加上现在剩余的
	 				users.setNXJTS(nxjts);
	 				usersService.updateNxjts(users);
	 				return "success";
	 			}
	 			return "error";
	 		}
	 		else{
	 			return "error";
	 		}
	 	}

		
		
		//4.审批日志页面sprzUI
		@RequestMapping(value = "sprzUI")
	 	public ModelAndView sprzUI() {
			String id=request.getParameter("id");
			System.out.println(id+"======");
			Leave leave=leaveService.getById(id);
			if(leave!=null){
				if(leave.getDysprid()!=null){
					Users users=usersService.getUserById(leave.getDysprid());
					map.put("dysprid", users.getZSXM());
					map.put("dysprjg", leave.getDysprjg());//第一审批人结果
					map.put("dyspryj", leave.getDyspryj());//第一审批人意见
					map.put("dysprsj", leave.getDysprsj());//第一审批人时间
				}else{
					map.put("dysprid", "");
					map.put("dysprjg", "");//第一审批人结果
					map.put("dyspryj", "");//第一审批人意见
					map.put("dysprsj", "");//第一审批人时间
				}
						
				if(leave.getDesprid()!=null){
					Users users=usersService.getUserById(leave.getDesprid());
					map.put("desprid", users.getZSXM());
					map.put("desprjg", leave.getDesprjg());//第二审批人结果
					map.put("despryj", leave.getDespryj());//第二审批人意见
					map.put("desprsj", leave.getDesprsj());//第二审批人时间
				}else{
					map.put("desprid", "");
					map.put("desprjg", "");//第二审批人结果
					map.put("despryj", "");//第二审批人意见
					map.put("desprsj", "");//第二审批人时间
				}
				if(leave.getDssprid()!=null){
					Users users=usersService.getUserById(leave.getDssprid());
					map.put("dssprid", users.getZSXM());
					map.put("dssprjg", leave.getDssprjg());//第三审批人结果
					map.put("dsspryj", leave.getDsspryj());//第三审批人意见
					map.put("dssprsj", leave.getDssprsj());//第三审批人时间
				}else{
					map.put("dssprid", "");
					map.put("dssprjg", "");//第三审批人结果
					map.put("dsspryj", "");//第三审批人意见
					map.put("dssprsj", "");//第三审批人时间
				}
				if(leave.getDsisprid()!=null){
					Users users=usersService.getUserById(leave.getDsisprid());
					map.put("dsisprid", users.getZSXM());
					map.put("dsisprjg", leave.getDsisprjg());//第四审批人结果
					map.put("dsispryj", leave.getDsispryj());//第四审批人意见
					map.put("dsisprsj", leave.getDsisprsj());//第四审批人时间
				}else{
					map.put("dsisprid", "");
					map.put("dsisprjg", "");//第四审批人结果
					map.put("dsispryj", "");//第四审批人意见
					map.put("dsisprsj", "");//第四审批人时间
				}
				
			}
			return new ModelAndView("/shenpiJsp/sprzUI",map);
		}
		
		
		
		//5.我的审批记录sphistory
		@RequestMapping(value="/sphistory")
		public ModelAndView sphistory(){
			//获取当前用户id
			String uid= (String) session.getAttribute("sessionId");
			//获取查询添加
	 		String ZSXM=request.getParameter("ZSXM");
	 		String pId2=request.getParameter("pId2");//部门名称
	 		String did=request.getParameter("did");//部门编号
	 		//保存查询条件
	 		request.setAttribute("ZSXM", ZSXM);
	 		request.setAttribute("pId2", pId2);
	 		request.setAttribute("did", did);
	 		//获取页码
			String currentPage=request.getParameter("currentPage");
	 		PageResult result = leaveService.getMySphistory(uid,ZSXM,did,currentPage);
	 		List<Leave> list =result.getList();
	 		//获取
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
	 		return new ModelAndView("/shenpiJsp/sphistory", map);
	 		
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
