package tellhow.cavate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import tellhow.cavate.mapper.DepartmentMapper;
import tellhow.cavate.mapper.HolidayMapper;
import tellhow.cavate.mapper.LeaveMapper;
import tellhow.cavate.mapper.ShenPiMapper;
import tellhow.cavate.mapper.UsersMapper;
import tellhow.cavate.pojo.Department;

public class BaseService {
	public HashMap<String,Object> param = new HashMap<String,Object>();
	@Autowired
	public HttpServletRequest request;
	@Autowired
	public HttpServletResponse response;
	@Autowired
	public HttpSession session;
	public String currentUser;
	public String uploadFilePath = "d:\\upload\\"; //定义文件上传保存的路径
	public List<Department> allList = new ArrayList<Department>();
	public List<Department> allList2 = new ArrayList<Department>();
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.currentUser = (String) this.session.getAttribute("userName");
	}
	@Autowired
	public DepartmentMapper departmentMapper;
	@Autowired
	public UsersMapper usersMapper;
	@Autowired
	public HolidayMapper holidayMapper;
	@Autowired
	public LeaveMapper leaveMapper;
	@Autowired
	public ShenPiMapper spMapper;
}
