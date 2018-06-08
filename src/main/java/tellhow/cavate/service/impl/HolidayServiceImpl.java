package tellhow.cavate.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tellhow.cavate.pojo.Holiday;
import tellhow.cavate.service.HolidayService;
import tellhow.cavate.utils.UuidUtil;
@Service
public class HolidayServiceImpl extends BaseService implements HolidayService{

	//1.查询所有节假日
	@Override
	public List<Holiday> getHolidays() {
		return holidayMapper.getHolidays();
	}

	//2.添加节假日
	@Override
	public boolean addHoliday(Holiday holiday) {
		holiday.setId(UuidUtil.get32UUID());
		return holidayMapper.addHoliday(holiday);
	}

	//3.判断节假日是否已经添加
	@Override
	public Holiday findExitHoliday(String holiday) {
		return holidayMapper.findExitHoliday(holiday);
	}

	//4.删除
	@Override
	public boolean deleteHoliday(String id) {
		return holidayMapper.deleteHoliday(id);
	}

}
