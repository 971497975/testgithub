package tellhow.cavate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

import tellhow.cavate.pojo.Department;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.utils.MD5;
import tellhow.cavate.utils.PageResult;
import tellhow.cavate.utils.TellhowResult;




@Controller
@RequestMapping("/user")
public class UsersCotroller extends BaseController{

     //1.根据警号或身份证号+密码,验证登录是否成功
     @RequestMapping(value="/login", method=RequestMethod.POST)
 	 @ResponseBody
 	 public TellhowResult userLogin(@RequestParam(value="policeNumOrID") String policeNumOrID, String pwd){
    	 try {
    		TellhowResult result=usersService.userLogin(policeNumOrID, pwd);
    		Users user = (Users) result.getData();
			if (user != null) {
				// 保存用户信息到session
				session.setAttribute("sessionUser", user);
				session.setAttribute("sessionJH", user.getJH());
				session.setAttribute("sessionId", user.getId());
				session.setAttribute("sessionZSXM", user.getZSXM());
				session.setAttribute("sessionDid", user.getDid());
				session.setAttribute("sessionZW", user.getZW());//职位
				session.setAttribute("sessionRole", user.getRole());//角色
			}
 			return result;
 		} catch (Exception e) {
 			e.printStackTrace();
 			return TellhowResult.build(500, "系统异常，请重新操作！");
 		}

     }
     
     
    //2.跳转到添加用户页面
 	@RequestMapping(value = "addUserUI")
 	public String addUserUI() {
 		// 获取当前用户所在部门id
 		String BMBH = (String) session.getAttribute("sessionDid");
 		toJsonString(BMBH);
 		return "/usersJsp/addUserUI";
 	}
 	
 	
    //3.验证用户是否已经添加，根据警号或者身份证号
 	@RequestMapping(value = "findExitUser")
 	@ResponseBody
 	public String findExitUser(Users user){
 		Users users=usersService.findExitUser(user);
 		if(users!=null){
 			return "error";//说明已经存在该用户
 		}
 		return "success";
 	}
 	
 	
 	//4.添加用户
 	@RequestMapping(value = "addUser")
 	@ResponseBody
 	public String addUser(Users user) {
 		boolean result=usersService.addUsers(user);
 		if(result){
 			return "success";
 		}
 		return "error";
 	}
 	
 	//跳转到修改用户页面
 	@RequestMapping(value = "updateUserUI")
 	public String updateUserUI(@RequestParam String id) {
 		// 获取当前用户所在部门id
 		String BMBH = (String) session.getAttribute("sessionDid");
 		toJsonString(BMBH);
 		Users users = usersService.getUserById(id);
 		request.setAttribute("users", users);
 		return "/usersJsp/updateUserUI";
 	}
 	
 	
 	//修改用户
 	@RequestMapping(value = "updateUser")
 	@ResponseBody
 	public String updateUser(Users user) {
 		boolean result=usersService.updateUsers(user);
 		if(result){
 			return "success";
 		}
 		    return "error";
 	}
 	
 	
 	//用户列表
 	@RequestMapping(value="/userList")
	public ModelAndView userList(){
 		//获取查询条件
 		String ZSXM=request.getParameter("ZSXM");
 		String pId2=request.getParameter("pId2");//部门名称
 		String did=request.getParameter("did");//部门编号
 		String JH=request.getParameter("JH");//部门编号
 		//保存查询条件
 		request.setAttribute("ZSXM", ZSXM);
 		request.setAttribute("pId2", pId2);
 		request.setAttribute("did", did);
 		request.setAttribute("JH", JH);
 	    // 获取当前用户所在部门id
 		String BMBH = (String) session.getAttribute("sessionDid");
 		//获取当前用户的权限
 		String Role = (String) session.getAttribute("sessionRole");
 		toJsonString(BMBH);
 		//封装查询条件
 		Users user=new Users();
 		user.setZSXM(ZSXM);
 		user.setJH(JH);
 		user.setDid(did);
 		
 		//获取页码
		String currentPage=request.getParameter("currentPage");
 		HashMap<String,Object> result = (HashMap<String, Object>)usersService.getUserList(currentPage,user,BMBH,Role);
 		map.put("pageResult", result.get("pageResult"));
 		request.getSession().setAttribute("totalPage", ((PageResult) result.get("pageResult")).getPage().getTotalPage());
		request.getSession().setAttribute("currentPage", ((PageResult) result.get("pageResult")).getPage().getCurrentPage());
 		return new ModelAndView("/usersJsp/userList", map);
 	}
 	
 	
 	
 	//6.删除用户
 	@RequestMapping(value = "delete")
 	@ResponseBody
 	public String delete(@RequestParam String id) {
 		boolean result=usersService.deleteUserById(id);
 		if(result){
 			return "success";
 		}
 		return "error";
 	}
 	
 	
 	//7.重置密码
 	@RequestMapping(value = "chongzhi")
 	@ResponseBody
 	public String chongzhi(@RequestParam String id) {
 		boolean result=usersService.chongzhi(id);
 		if(result){
 			return "success";
 		}
 		return "error";
 	}
 	
    // 12.我的信息页面
 	@RequestMapping(value = "myinfo")
 	public ModelAndView myinfo() {
 		String id = (String) session.getAttribute("sessionId");
 		Users user = usersService.getUserById(id);
 		request.setAttribute("user", user);
 		return new ModelAndView("/usersJsp/myinfo");
 	}

 	// 13.修改密码页面
 	@RequestMapping(value = "changePwdUI")
 	public String changePwdUI() {
 		return "/usersJsp/changePwdUI";
 	}
 	
 	
 	//14. 查询旧密码是否正确
	@RequestMapping(value = "findPwd")
	@ResponseBody
	public String findPwd(String MM) {
		String id = (String) session.getAttribute("sessionId");
		Users user = usersService.getUserById(id);
		if (user.getMM().equalsIgnoreCase(MD5.md5(MM))) {
			return "success";
		} else {
			return "error";
		}
	}

	//16. 修改密码
	@RequestMapping(value = "changepwdok")
	@ResponseBody
	public String changepwdok() {
		String id = (String) session.getAttribute("sessionId");
		String MM = MD5.md5(request.getParameter("newMM"));
		Users user = new Users();
		user.setId(id);
		user.setMM(MM);
		boolean result = usersService.editUserPwd(user);
		if (result) {
			return "success";
		} else {
			return "error";
		}
	}

	//17.用户信息
 	@RequestMapping(value = "userInfo")
 	public ModelAndView userInfo() {
 		// 获取用户id
 		String id=request.getParameter("id");
 		Users user = usersService.getUserById(id);
 		map.put("user", user);
 		return new ModelAndView("/usersJsp/userInfo",map);
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
