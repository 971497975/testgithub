package tellhow.cavate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import tellhow.cavate.pojo.Users;
import tellhow.cavate.utils.TellhowResult;

public interface UsersMapper {

	//1.根据警号或身份证号+密码,验证登录是否成功
	Users userLogin(String policeNumOrID,String pwd);
	
	//2.根据警号或者身份证号查找用户是否存在 
	Users findExitUser(Users user);
	
	//3.添加用户
	boolean addUsers(Users user);

	//修改用户
	boolean updateUsers(Users user);
	
	//4.用户列表
	List<Users> getUserList(HashMap<String, Object> param);
	
	//5.系统用户数量
	int getUserCount(HashMap<String, Object> param);
	
	//6.查询用户信息
	List<Users> getQueryUserList(HashMap<String, Object> param);
	
	//7.查询用户数量
	int getQueryUserCount(HashMap<String, Object> param);
	
	//8.根据id删除用户信息
	boolean deleteUserById(String id);
	
	//9.重置密码
	boolean chongzhi(String id);
	
	//10.根据id查找用户信息
	Users getUserById(String id);

	//11.修改密码
	boolean editUserPwd(Users user);

	//12.跟新用户年休假
	void updateNXJTS(Users user);

	//13.获取审批人信息
	List<Users> getSprList(String did, String zW);

	//14.获取政监处审批用户
	List<Users> getZjSprList(String did);

	//15.获取高警总队主要领导
	List<Users> getGjSprList(String did);

	//16.获取分管该部门的分管局领导
	List<Users> getFgSprList(@Param("did")String did);

	//17.局主要领导
	List<Users> getZySprList();

	//18.根据警号查找用户信息
	Users getByJH(String jH);


}
