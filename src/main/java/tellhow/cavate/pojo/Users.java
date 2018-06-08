package tellhow.cavate.pojo;

import java.io.Serializable;
//用户实体类
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
public class Users implements Serializable{
      private String id;
      private String JH;//警号
      private String SFZH;//身份证号
      private String MM;//密码
      private String ZSXM;//真实姓名
      private String XB;//性别    0男     1女
      private String HF;//婚否   0否     1是
      @DateTimeFormat(pattern="yyyy-MM-dd")
      private Date CJGZSJ;//参加工作时间
      private Integer NXJTS;//年休假天数
      private String ZW;//职务
      private String ZWMC;//职务名称
      private String JG;//籍贯
      private String role;//角色      00系统管理员    01部门管理员     03普通用户
      private String did;//所属部门
      private String bmmc;//部门名称
      @DateTimeFormat(pattern="yyyy-MM-ddHH:mm:ss") 
      private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJH() {
		return JH;
	}
	public void setJH(String jH) {
		JH = jH;
	}
	public String getSFZH() {
		return SFZH;
	}
	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}
	
	public String getMM() {
		return MM;
	}
	public void setMM(String mM) {
		MM = mM;
	}
	public String getZSXM() {
		return ZSXM;
	}
	public void setZSXM(String zSXM) {
		ZSXM = zSXM;
	}
	public String getXB() {
		return XB;
	}
	public void setXB(String xB) {
		XB = xB;
	}
	public String getHF() {
		return HF;
	}
	public void setHF(String hF) {
		HF = hF;
	}
	
	public Date getCJGZSJ() {
		return CJGZSJ;
	}
	public void setCJGZSJ(Date cJGZSJ) {
		CJGZSJ = cJGZSJ;
	}
	public String getZW() {
		return ZW;
	}
	public void setZW(String zW) {
		ZW = zW;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getNXJTS() {
		return NXJTS;
	}
	public void setNXJTS(Integer nXJTS) {
		NXJTS = nXJTS;
	}
	public String getJG() {
		return JG;
	}
	public void setJG(String jG) {
		JG = jG;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getZWMC() {
		return ZWMC;
	}
	public void setZWMC(String zWMC) {
		ZWMC = zWMC;
	}
      
      
      
      
	
      
}
