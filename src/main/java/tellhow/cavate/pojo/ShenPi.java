package tellhow.cavate.pojo;

import java.io.Serializable;
import java.util.Date;

//审批实体类
public class ShenPi implements Serializable{
    private String id;
    private String lid;//哪个请假单的
    private String bmbh;//哪个部门的请假单
    private String sprzw;//审批人职位
    private String spr;//审批人的id
    private Date  spsj;//审批时间
    private String spjg;// 01审批中    02审批通过   03审批不通过
    private String spyj;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	
	public String getBmbh() {
		return bmbh;
	}
	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}
	public String getSprzw() {
		return sprzw;
	}
	public void setSprzw(String sprzw) {
		this.sprzw = sprzw;
	}
	public String getSpr() {
		return spr;
	}
	public void setSpr(String spr) {
		this.spr = spr;
	}
	public Date getSpsj() {
		return spsj;
	}
	public void setSpsj(Date spsj) {
		this.spsj = spsj;
	}
	public String getSpjg() {
		return spjg;
	}
	public void setSpjg(String spjg) {
		this.spjg = spjg;
	}
	public String getSpyj() {
		return spyj;
	}
	public void setSpyj(String spyj) {
		this.spyj = spyj;
	}
      
      
}
