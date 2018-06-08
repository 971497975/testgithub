package tellhow.cavate.mapper;

import java.util.HashMap;
import java.util.List;


import tellhow.cavate.pojo.Leave;

public interface LeaveMapper {

	//1.添加请假
	boolean addLeave(Leave leave);
	
	//2.我的请假单数量
	int getMyListCount(HashMap<String, Object> param);
	
	//3.我的请假单列表
	List<Leave> getMyList(HashMap<String, Object> param);

	//4.修改请假单审批状态
	boolean updateSpState(String id, String spState);

	//5.根据id获取请假单信息
	Leave getById(String id);

	//6.我的审批记录数量
	int getMySphistoryCount(HashMap<String, Object> param);

	//7.我的审批记录列表
	List<Leave> getMySphistory(HashMap<String, Object> param);

	//8.我的审批通过数量
	int getMyXjListCount(HashMap<String, Object> param);

	//9.审批通过列表
	List<Leave> getMyXjList(HashMap<String, Object> param);

	//10.跟新销假
	boolean updateXJ(Leave leave);

	//11.获取当前用户有多少待销假的请假单
	int getDXJCount(String uid);

	//12.删除请假单
	boolean deleteById(String id);

	//13.系统请假单数量
	int getXtListCount(HashMap<String, Object> param);

	//14.系统请假单列表
	List<Leave> getXtList(HashMap<String, Object> param);

	//15.系统销假单数量
	int getXtxjListCount(HashMap<String, Object> param);

	//16.系统销假单列表
	List<Leave> getXtxjList(HashMap<String, Object> param);
	
	
}
