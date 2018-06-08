package tellhow.cavate.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

//请假单实体类
public class Leave implements Serializable{
      private String id;
      @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date startDate;//休假开始时间
      @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date endDate;//休假结束时间
      private Integer days;//休了多少天
      private Integer otherDays;//双休、法定
      private Integer nxjts;//请假中包含年休假的天数
      private String leaveType;//休假类别
      private String cause;//休假事由
      private String spState;//审状态    00待审批      01审批中    02审批通过   03审批不通过
      private String createUser;//这张请假单是谁的
      private Date createTime;//创建时间
      //审批信息
      private String dysprid;//第一审批人id
      private String dyspryj;//第一审批人意见
      private String dysprjg;//第一审批结果    00待审批     01同意    02不同意
      private Date dysprsj;//第一审批人时间
      
      private String desprid;//第二审批人id
      private String despryj;//第二审批人意见
      private String desprjg;//第二审批结果    00待审批     01同意    02不同意
      private Date desprsj;//第二审批人时间
      
      private String dssprid;//第三审批人id
      private String dsspryj;//第三审批人意见
      private String dssprjg;//第三审批结果    00待审批     01同意    02不同意
      private Date dssprsj;//第三审批人时间
      
      private String dsisprid;//第四审批人id
      private String dsispryj;//第四审批人意见
      private String dsisprjg;//第四审批结果    00待审批     01同意    02不同意
      private Date dsisprsj;//第四审批人时间
      
      private String sfxj;//是否销假   00未销假      01代表已销假
      private Date xjsj;//销假时间
      private String xjzt;//销假状态  01 正常     02延期     03提前
      private String xjly;//销假理由,延期和提前将会出现
      
      
      
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public Integer getOtherDays() {
		return otherDays;
	}
	public void setOtherDays(Integer otherDays) {
		this.otherDays = otherDays;
	}
	public Integer getNxjts() {
		return nxjts;
	}
	public void setNxjts(Integer nxjts) {
		this.nxjts = nxjts;
	}
	public String getSpState() {
		return spState;
	}
	public void setSpState(String spState) {
		this.spState = spState;
	}
	public String getDysprid() {
		return dysprid;
	}
	public void setDysprid(String dysprid) {
		this.dysprid = dysprid;
	}
	public String getDyspryj() {
		return dyspryj;
	}
	public void setDyspryj(String dyspryj) {
		this.dyspryj = dyspryj;
	}
	public String getDysprjg() {
		return dysprjg;
	}
	public void setDysprjg(String dysprjg) {
		this.dysprjg = dysprjg;
	}
	public Date getDysprsj() {
		return dysprsj;
	}
	public void setDysprsj(Date dysprsj) {
		this.dysprsj = dysprsj;
	}
	public String getDesprid() {
		return desprid;
	}
	public void setDesprid(String desprid) {
		this.desprid = desprid;
	}
	public String getDespryj() {
		return despryj;
	}
	public void setDespryj(String despryj) {
		this.despryj = despryj;
	}
	public String getDesprjg() {
		return desprjg;
	}
	public void setDesprjg(String desprjg) {
		this.desprjg = desprjg;
	}
	public Date getDesprsj() {
		return desprsj;
	}
	public void setDesprsj(Date desprsj) {
		this.desprsj = desprsj;
	}
	public String getDssprid() {
		return dssprid;
	}
	public void setDssprid(String dssprid) {
		this.dssprid = dssprid;
	}
	public String getDsspryj() {
		return dsspryj;
	}
	public void setDsspryj(String dsspryj) {
		this.dsspryj = dsspryj;
	}
	public String getDssprjg() {
		return dssprjg;
	}
	public void setDssprjg(String dssprjg) {
		this.dssprjg = dssprjg;
	}
	public Date getDssprsj() {
		return dssprsj;
	}
	public void setDssprsj(Date dssprsj) {
		this.dssprsj = dssprsj;
	}
	public String getDsisprid() {
		return dsisprid;
	}
	public void setDsisprid(String dsisprid) {
		this.dsisprid = dsisprid;
	}
	public String getDsispryj() {
		return dsispryj;
	}
	public void setDsispryj(String dsispryj) {
		this.dsispryj = dsispryj;
	}
	public String getDsisprjg() {
		return dsisprjg;
	}
	public void setDsisprjg(String dsisprjg) {
		this.dsisprjg = dsisprjg;
	}
	public Date getDsisprsj() {
		return dsisprsj;
	}
	public void setDsisprsj(Date dsisprsj) {
		this.dsisprsj = dsisprsj;
	}
	public String getSfxj() {
		return sfxj;
	}
	public void setSfxj(String sfxj) {
		this.sfxj = sfxj;
	}
	public Date getXjsj() {
		return xjsj;
	}
	public void setXjsj(Date xjsj) {
		this.xjsj = xjsj;
	}
	public String getXjzt() {
		return xjzt;
	}
	public void setXjzt(String xjzt) {
		this.xjzt = xjzt;
	}
	public String getXjly() {
		return xjly;
	}
	public void setXjly(String xjly) {
		this.xjly = xjly;
	}
      
      
}
