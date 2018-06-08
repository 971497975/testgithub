package tellhow.cavate.pojo;

import java.io.Serializable;



public class Department implements Serializable{
    private String BMBH;//部门编号
    private String BMMC;//部门名称
    private String SJBM;//上级部门
	public String getBMBH() {
		return BMBH;
	}
	public void setBMBH(String bMBH) {
		BMBH = bMBH;
	}
	public String getBMMC() {
		return BMMC;
	}
	public void setBMMC(String bMMC) {
		BMMC = bMMC;
	}
	public String getSJBM() {
		return SJBM;
	}
	public void setSJBM(String sJBM) {
		SJBM = sJBM;
	}
    
    
}
