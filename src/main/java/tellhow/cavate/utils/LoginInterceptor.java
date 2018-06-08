package tellhow.cavate.utils;

import java.io.PrintStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//登录验证拦截
public class LoginInterceptor implements HandlerInterceptor{

	//1.整个请求处理完毕回调方法，即在视图渲染完毕时回调
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object arg2, Exception arg3)throws Exception {
		 
	}

	//2.后处理回调方法，实现处理器的后处理
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object arg2, ModelAndView model) throws Exception {
 
	}

	//3.预处理回调方法，实现处理器的预处理（如登录检查）第三个参数为响应的处理器
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
		//获取请求的URL  
        String url = request.getRequestURI(); 
        System.out.println(url);
        //首页和登录方法放行,因为这些方法都是没有session的
        if(url.equals("/vacate/")||url.equals("/vacate")||url.indexOf("user/login.do")>=0||url.indexOf("outLogin.do")>=0||url.indexOf("main.do")>=0){  
            return true;  
        }  
        //获取Session  
        HttpSession session = request.getSession();  
        String username = (String)session.getAttribute("sessionJH");  
        if(username != null){  
            return true;//true表示继续流程（如调用下一个拦截器或处理器） 
        }
        else{
        	String path = request.getContextPath();
        	response.sendRedirect(path+"/outPast.do");
            //false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
            return false; 
        }
         
	}

}
