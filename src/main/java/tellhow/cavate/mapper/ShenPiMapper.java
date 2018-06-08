package tellhow.cavate.mapper;

import java.util.List;

import tellhow.cavate.pojo.Leave;
import tellhow.cavate.pojo.ShenPi;

public interface ShenPiMapper {
    
	//1.我的审批信息
	List<Leave> getMySpList(String uid);

	//2.跟新请假单审批信息
	boolean updateLeaveById(Leave leave);

}
