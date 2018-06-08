package tellhow.cavate.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

import tellhow.cavate.pojo.Department;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.service.UsersService;
import tellhow.cavate.utils.DateUtil;
import tellhow.cavate.utils.MD5;
import tellhow.cavate.utils.Page;
import tellhow.cavate.utils.PageResult;
import tellhow.cavate.utils.PageUtil;
import tellhow.cavate.utils.TellhowResult;
import tellhow.cavate.utils.UuidUtil;

@Service
public class UsersServiceImpl extends BaseService implements UsersService{
	
	//1.根据警号或身份证号+密码,验证登录是否成功
	@Override
	public TellhowResult userLogin(String policeNumOrID, String pwd) {
		pwd=MD5.md5(pwd);//密码加密
		Users user=usersMapper.userLogin(policeNumOrID, pwd);
		if(user==null){
			return TellhowResult.build(400, "用户名或密码错误！");
		}
		return TellhowResult.ok(user);
	}

    //2.验证用户是否已经存在
	@Override
	public Users findExitUser(Users user) {
		Users users=usersMapper.findExitUser(user);
		return users;
	}

	//3.添加用户信息
	@Override
	public boolean addUsers(Users user) {
		user.setMM(MD5.md5("123"));
		user.setId(UuidUtil.get32UUID());
		user.setCreateTime(new Date());
		//根据参加工作时间计算可休年休假天数
		String inworkYear = DateUtil.ChangeTime2(user.getCJGZSJ());
		String nowYear = DateUtil.ChangeTime2(new Date());
		int publicLeave = DateUtil.getDiffYear(inworkYear, nowYear);
		if (publicLeave >= 1 && publicLeave < 10) {
			user.setNXJTS(5);//1-10年，年休假5天
		} else if (publicLeave >= 10 && publicLeave < 20) {
			user.setNXJTS(10);//10-20年，年休假10天
		} else if (publicLeave >= 20) {
			user.setNXJTS(15);//大于20年，年休假15天
		}else {
			user.setNXJTS(0);//小于1年没有年休假
		}
		boolean result=usersMapper.addUsers(user);
		return result;
	}

	//4.获取用户列表
	@Override
	public Map getUserList(String currentPages,Users user,String BMBH,String role) {
		//获取所有部门
		List<Department> allDepartments=departmentMapper.getAllDepartments();
		List<Department> suborList = new ArrayList<Department>();
		List<String> uidList = new ArrayList<String>();
		allList.clear();
		allList2.clear();
		//如果是系统管理员，查询交管局下所有
		if(role.equals("00")){
			BMBH="360000000000";
		}
		suborList = digui(BMBH,allDepartments);
		Department department=departmentMapper.getDepartmentById(BMBH);
		suborList.add(department);
		// 获取当前用户所在单位的下级所有单位的id
		for (int i = 0; i < suborList.size(); i++) {
			uidList.add(suborList.get(i).getBMBH());
		}
		//封装查询条件
		param.put("uidList", uidList);
		param.put("BMBH", BMBH);
		param.put("user", user);
		//处理页码
		int currentPage = 0;
		if (currentPages != null) {
			currentPage = Integer.parseInt(currentPages);
		}
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),usersMapper.getUserCount(param));
		param.put("page", page);
		List<Users> list=usersMapper.getUserList(param);
		PageResult pageResult = new PageResult(page, list);
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("pageResult", pageResult);
		return result;
	}

	//5.条件查询用户
	@Override
	public Map getQueryUserList(String currentPages, Users user) {
		HashMap<String,Object> param = new HashMap<String,Object>();
		param.put("user", user);
		//处理页码
		int currentPage = 0;
		if (currentPages != null) {
			currentPage = Integer.parseInt(currentPages);
		}
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page = PageUtil.getPage(page.getPageRecord(),page.getCurrentPage(),usersMapper.getQueryUserCount(param));
		param.put("page", page);
		List<Users> list=usersMapper.getQueryUserList(param);
		PageResult pageResult = new PageResult(page, list);
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("pageResult", pageResult);
		return result;
	}

	//6.删除用户
	@Override
	public boolean deleteUserById(String id) {
		boolean result=usersMapper.deleteUserById(id);
		return result;
	}

	//7.重置密码
	@Override
	public boolean chongzhi(String id) {
		boolean result=usersMapper.chongzhi(id);
		return result;
	}

	//8.根据用户id查找用户
	@Override
	public Users getUserById(String id) {
		return usersMapper.getUserById(id);
	}
    
	//9.修改密码
	@Override
	public boolean editUserPwd(Users user) {
		return usersMapper.editUserPwd(user);
	}

	//10.获取审批人信息
	@Override
	public List<Users> getSprList(String did, String ZW) {
		return usersMapper.getSprList(did, ZW);
	}

	//11.获取政监处审批用户
	@Override
	public List<Users> getZjSprList(String did) {
		return usersMapper.getZjSprList(did);
	}

	//12.获取高警总队主要领导
	@Override
	public List<Users> getGjSprList(String did) {
		return usersMapper.getGjSprList(did);
	}

	//13.获取分管该部门的分管局领导
	@Override
	public List<Users> getFgSprList(String did) {
		return usersMapper.getFgSprList(did);
	}

	//14.局主要领导
	@Override
	public List<Users> getZySprList() {
		return usersMapper.getZySprList();
	}

	//15.不同意请假后要归还年休假天数
	@Override
	public void updateNxjts(Users users) {
		usersMapper.updateNXJTS(users);
	}

    //16.修改用户信息
	@Override
	public boolean updateUsers(Users user) {
		return usersMapper.updateUsers(user);
	}

	//17.根据警号查找用户信息
	@Override
	public Users getByJH(String JH) {
		return usersMapper.getByJH(JH);
	}

	//递归算法,获取当前用户所在党组织以及下级所有党组织
	public List<Department> digui(String pid,List<Department> alldepList){
		// 获取一级单位信息
 		if (pid != null) {
 			for(int j=0;j<alldepList.size();j++){
 				//查找哪些部门的上级部门是传进来的部门
 				if(alldepList.get(j).getSJBM()!=null){
 					if(alldepList.get(j).getSJBM().equals(pid)){
 	 					allList2.add(alldepList.get(j));
 	 					digui(alldepList.get(j).getBMBH(),alldepList);
 	 				}
 				}
 			}
 		}
 		return allList2;
	}

}
