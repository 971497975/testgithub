package tellhow.cavate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//控制页面跳转的controller
@Controller
public class PageController extends BaseController{
	
	    //1.http://localhost:8080  跳转到登录页面
		@RequestMapping("/")
		public String showIndex() {
			return "login";
		}
		
		//2.登录成功后跳转到main页面
		@RequestMapping("/main")
		public String main() {
			return "main";
		}
		
		//3.用户退出页面
		@RequestMapping("outLogin")
		public String outLogin() {
			//清空session
			session.removeAttribute("sessionUser");
			session.removeAttribute("sessionJH");
			session.removeAttribute("sessionId");
			session.removeAttribute("sessionZSXM");
			session.removeAttribute("sessionDid");
			return showIndex();
		}
		
		//4.用户过期
		@RequestMapping("outPast")
		public String outPast() {
			//清空session
			session.removeAttribute("sessionUser");
			session.removeAttribute("sessionJH");
			session.removeAttribute("sessionId");
			session.removeAttribute("sessionZSXM");
			session.removeAttribute("sessionDid");
			return "backtouserLogin";
		}
}
