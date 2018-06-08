package tellhow.cavate.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tellhow.cavate.pojo.Holiday;
import tellhow.cavate.pojo.Users;


@Controller
@RequestMapping("/holiday")
public class HolidayController extends BaseController{

	    //1.节假日列表
	 	@RequestMapping(value="/holidayList")
		public ModelAndView holidayList(){
	 		List<Holiday> list=holidayService.getHolidays();
	 		map.put("list", list);
	 		return new ModelAndView("/holidayJsp/holidayList", map);
	 	}
	 	
	 	
	 	//2.添加节假日页面
	 	@RequestMapping(value = "addHolidayUI")
	 	public String addHolidayUI() {
	 		return "/holidayJsp/addHolidayUI";
	 	}
	 	
	 	//3.验证用户是否已经添加当前日期
	 	@RequestMapping(value = "findExitHoliday")
	 	@ResponseBody
	 	public String findExitHoliday(){
	 		String holidayDate=request.getParameter("holidayDate");
	 		Holiday result=holidayService.findExitHoliday(holidayDate);
	 		if(result!=null){
	 			return "error";//说明已经存在
	 		}
	 		return "success";
	 	}
	 	
	 	
	 	//4.添加节假日
	 	@RequestMapping(value = "addHoliday")
	 	@ResponseBody
	 	public String addUser(Holiday holiday) {
	 		boolean result=holidayService.addHoliday(holiday);
	 		if(result){
	 			return "success";
	 		}
	 		return "error";
	 	}
	 	
	 	
	 	//5.删除
	 	@RequestMapping(value = "deleteHoliday")
	 	@ResponseBody
	 	public String delete(@RequestParam String id) {
	 		boolean result=holidayService.deleteHoliday(id);
	 		if(result){
	 			return "success";
	 		}
	 		    return "error";
	 	}	
}
