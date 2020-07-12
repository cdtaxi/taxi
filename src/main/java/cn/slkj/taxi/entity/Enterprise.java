package cn.slkj.taxi.entity;

/**
 * 

* <p>Title: Company</p>  

* <p>Description: </p>  

* @author wangling  

* @date 2018年10月30日
 */
public class Enterprise {
	private String id;
	private String unitname; //单位名称
	private String phone; //联系电话
	private String unitaddr;// 单位地址
	private String businessscope;// 经营范围
	private String businessno; //经营许可证号
	private String enterprisecode;// 企业代码
	private String regcapital; //注册资本
	private String economytype; //经济类型
	private String legalrepresentative;//法人代表 
	private String vehiclesnum; //车辆总数
	private String employeescount;// 从业人员总数
	private String manageersonnel; //安全管理人员总数
	private String carnum; //企业自有车辆数
	private String addtime;//插入日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnitname() {
		return unitname;
	}
	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUnitaddr() {
		return unitaddr;
	}
	public void setUnitaddr(String unitaddr) {
		this.unitaddr = unitaddr;
	}
	public String getBusinessscope() {
		return businessscope;
	}
	public void setBusinessscope(String businessscope) {
		this.businessscope = businessscope;
	}
	public String getBusinessno() {
		return businessno;
	}
	public void setBusinessno(String businessno) {
		this.businessno = businessno;
	}
	public String getEnterprisecode() {
		return enterprisecode;
	}
	public void setEnterprisecode(String enterprisecode) {
		this.enterprisecode = enterprisecode;
	}
	public String getRegcapital() {
		return regcapital;
	}
	public void setRegcapital(String regcapital) {
		this.regcapital = regcapital;
	}
	public String getEconomytype() {
		return economytype;
	}
	public void setEconomytype(String economytype) {
		this.economytype = economytype;
	}
	public String getLegalrepresentative() {
		return legalrepresentative;
	}
	public void setLegalrepresentative(String legalrepresentative) {
		this.legalrepresentative = legalrepresentative;
	}
	public String getVehiclesnum() {
		return vehiclesnum;
	}
	public void setVehiclesnum(String vehiclesnum) {
		this.vehiclesnum = vehiclesnum;
	}
	public String getEmployeescount() {
		return employeescount;
	}
	public void setEmployeescount(String employeescount) {
		this.employeescount = employeescount;
	}
	public String getManageersonnel() {
		return manageersonnel;
	}
	public void setManageersonnel(String manageersonnel) {
		this.manageersonnel = manageersonnel;
	}
	public String getCarnum() {
		return carnum;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", unitname=" + unitname + ", phone="
				+ phone + ", unitaddr=" + unitaddr + ", businessscope="
				+ businessscope + ", businessno=" + businessno
				+ ", enterprisecode=" + enterprisecode + ", regcapital="
				+ regcapital + ", economytype=" + economytype
				+ ", legalrepresentative=" + legalrepresentative
				+ ", vehiclesnum=" + vehiclesnum + ", employeescount="
				+ employeescount + ", manageersonnel=" + manageersonnel
				+ ", carnum=" + carnum + ", addtime=" + addtime + "]";
	}
	
	

}
