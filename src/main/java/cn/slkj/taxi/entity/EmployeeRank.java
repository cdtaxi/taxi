package cn.slkj.taxi.entity;

public class EmployeeRank
{
  private String id;
  private String pid;//人员id
  private String year;//年份
  private String rank;//评级
  private String create_time;//添加时间
  private String name;//姓名
  private String sex;//性别
  private String borndate;//出生日期
  private String idcard;// 身份证号码
  private String educated;// 教育程度
  private String cyzg_card;// 从业资格证号
  private String carid;// 车牌号
  private String phone;// 联系方式
  private String company;// 公司
  private String status;// 状态
  
  
  public String getId() {
	return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getYear()
  {
    return this.year;
  }
  
  public void setYear(String year)
  {
    this.year = year;
  }
  
  public void setRank(String rank)
  {
    this.rank = rank;
  }
  
  public String getRank()
  {
    return this.rank;
  }
  
  public String getPid()
  {
    return this.pid;
  }
  
  public void setPid(String pid)
  {
    this.pid = pid;
  }

	public String getCreate_time() {
		return create_time;
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBorndate() {
		return borndate;
	}

	public void setBorndate(String borndate) {
		this.borndate = borndate;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getEducated() {
		return educated;
	}

	public void setEducated(String educated) {
		this.educated = educated;
	}

	public String getCyzg_card() {
		return cyzg_card;
	}

	public void setCyzg_card(String cyzg_card) {
		this.cyzg_card = cyzg_card;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
  
}
