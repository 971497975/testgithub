package tellhow.cavate.mapper;

import java.util.List;

import tellhow.cavate.pojo.Holiday;

public interface HolidayMapper {

	//1.查询所有节假日
	List<Holiday> getHolidays();
	
	//2.添加节假日
	boolean addHoliday(Holiday holiday);
	
	//3.判断节假日是否已经添加
	Holiday findExitHoliday(String holiday);
	
	//4.删除节假日
	boolean deleteHoliday(String id);
	
}
