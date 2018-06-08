package tellhow.cavate.pojo;

import java.io.Serializable;

//节假日实体类
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
public class Holiday implements Serializable{
    private String id;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date holidayDate;//放假的日期

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getHolidayDate() {
		return holidayDate;
	}
	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}
      
}
