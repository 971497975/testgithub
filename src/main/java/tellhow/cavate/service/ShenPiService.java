package tellhow.cavate.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tellhow.cavate.pojo.Leave;

public interface ShenPiService {

	//1.获取带我审批请假单
	List<Leave> getMySpList(String uid);

	//2.跟新请假单审批信息
	boolean updateLeaveById(Leave leave);

	
}
