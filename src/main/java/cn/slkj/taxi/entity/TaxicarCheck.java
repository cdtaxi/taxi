package cn.slkj.taxi.entity;

/**
 * 

* <p>Title: TaxicarCheck</p>  

* <p>Description: </p>  

* @author wangling  

* @date 2018年10月30日
 */
public class TaxicarCheck {
	private String id;
	private String operatingnum; //营运证号
	private String annualdate; //年审日期
	private String nextannualdate; //终止日期
	private String vehicle; //车辆级别
	private String addtime; //插入日期
	private String status;//审核状态
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOperatingnum() {
		return operatingnum;
	}
	public void setOperatingnum(String operatingnum) {
		this.operatingnum = operatingnum;
	}
	public String getAnnualdate() {
		return annualdate;
	}
	public void setAnnualdate(String annualdate) {
		this.annualdate = annualdate;
	}
	public String getNextannualdate() {
		return nextannualdate;
	}
	public void setNextannualdate(String nextannualdate) {
		this.nextannualdate = nextannualdate;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "TaxicarCheck [id=" + id + ", operatingnum=" + operatingnum
				+ ", annualdate=" + annualdate + ", nextannualdate="
				+ nextannualdate + ", vehicle=" + vehicle + ", addtime="
				+ addtime + ", status=" + status + "]";
	}
	
	

}
