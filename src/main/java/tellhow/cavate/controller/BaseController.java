package tellhow.cavate.controller;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import tellhow.cavate.service.DepartmentService;
import tellhow.cavate.service.HolidayService;
import tellhow.cavate.service.LeaveService;
import tellhow.cavate.service.ShenPiService;
import tellhow.cavate.service.UsersService;

public class BaseController {
	
	public Map<String, Object> map = new HashMap<String, Object>();
	public Map<String, Object> pageMap = new HashMap<String, Object>();
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public String currentUser;
	public String uploadFileTMPath = "D:\\upload\\threeMeets"; //定义文件上传保存的路径  三会 
	public String uploadFileETPath = "D:\\upload\\educationTraining"; //定义文件上传保存的路径  教育培训
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.currentUser = (String) this.session.getAttribute("userName");
	}
	
	@Autowired
	public UsersService usersService;
	
	@Autowired
	public DepartmentService departmentService;
	
	@Autowired
	public HolidayService holidayService;
	
	@Autowired
	public LeaveService leaveService;
	
	@Autowired
	public ShenPiService spService;
	
}
