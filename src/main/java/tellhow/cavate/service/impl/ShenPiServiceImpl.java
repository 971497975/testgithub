package tellhow.cavate.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import sun.tools.tree.ArrayAccessExpression;
import tellhow.cavate.pojo.Leave;
import tellhow.cavate.pojo.ShenPi;
import tellhow.cavate.pojo.Users;
import tellhow.cavate.service.ShenPiService;

@Service
public class ShenPiServiceImpl extends BaseService implements ShenPiService{

	
	//1.获取待我审批请假单
	@Override
	public List<Leave> getMySpList(String uid) {
		//1.2.3.4审批人id字段为当前用户id并且对应的审批结果为空
		return spMapper.getMySpList(uid);
	}

	//2.跟新请假单审批信息
	@Override
	public boolean updateLeaveById(Leave leave) {
		return spMapper.updateLeaveById(leave);
	}

	
}
