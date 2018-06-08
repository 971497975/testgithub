package tellhow.cavate.service;

import java.util.List;
import java.util.Map;

import tellhow.cavate.pojo.Users;
import tellhow.cavate.utils.TellhowResult;

public interface UsersService {

	    //1.根据警号或身份证号+密码,验证登录是否成功
		TellhowResult userLogin(String policeNumOrID,String pwd);
		//2.根据警号或者身份证号查找用户是否存在 
		Users findExitUser(Users user);
		//3.添加用户信息
		boolean addUsers(Users user);
		//修改用户
		boolean updateUsers(Users user);
		//4.系统用户列表
		Map getUserList(String currentPage, Users user, String bMBH, String role);
		//5.条件查询用户
		Map getQueryUserList(String currentPage,Users user);
		//6.删除
		boolean deleteUserById(String id);
		//7.重置密码
		boolean chongzhi(String id);
		//8.根据用户id查找用户
		Users getUserById(String id);
		//9.修改密码
		boolean editUserPwd(Users user);
		//10.获取审批人信息
		List<Users> getSprList(String did, String ZW);
		//11.获取政监处审批用户
		List<Users> getZjSprList(String did);
		//12.获取高警总队主要负责人
		List<Users> getGjSprList(String did);
		//13.获取分管该部门的分管局领导
		List<Users> getFgSprList(String did);
		//14.局主要领导
		List<Users> getZySprList();
		//15.不同意请假后要归还年休假天数
		void updateNxjts(Users users);
		//16.根据警号查找用户信息
		Users getByJH(String string);
 }
