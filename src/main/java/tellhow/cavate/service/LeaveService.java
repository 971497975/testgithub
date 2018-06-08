package tellhow.cavate.service;



import tellhow.cavate.pojo.Leave;
import tellhow.cavate.utils.PageResult;

public interface LeaveService {

	//1.添加请假单
	String addLeave(Leave leave);
	
	//2.我的请假单列表
	PageResult getMyList(String currentPage);

	//3.根据id查找请假单信息
	Leave getById(String id);

	//4.我的审批记录
	PageResult getMySphistory(String uid, String zSXM, String did, String currentPage);

	//5.需要销假的请假单 ,只有审批通过才可以销假
	PageResult getMyXjList(String currentPage);

	//6.跟新销假
	boolean updateXJ(Leave leave);

	//7.获取当前用户有多少待销假的请假单
	int getDXJCount(String uid);

	//8.删除请假单
	boolean deleteById(String id);

	//9.系统请假单列表
	PageResult getXtList(String currentPage, String zSXM, String did, String spState);

	//10.系统销假单列表
	PageResult getXtxjList(String currentPage, String zSXM, String did, String sfxj);

}
