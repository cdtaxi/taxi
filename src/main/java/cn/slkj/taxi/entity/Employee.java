/**
 * 
 */
package cn.slkj.taxi.entity;

import java.io.InputStream;
import java.util.Date;

import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.Tools;


/**
 * @author maxuh 从业人员
 */

public class Employee extends EmployeeRank {
	private String id;
	private String personalId;// 档案号 监督卡号
	private String permitFilesId;// 许可案卷编号
	private String name;// 姓名
	private String sex;// 性别
	private String borndate;// 出生年月
	private String nationality;// 国籍
	private String idcard;// 身份证号码
	private String educated;// 教育程度
	private String phone;// 联系方式
	private String address;// 住址
	private String driveCard;// 驾驶证号
	private String driveStartDate;// 驾驶证初领日期
	private String driveType;// 准驾车型
	private String cyzgCard;// 从业资格证号
	private String registerDate;// 注册时间
	private String cancelDate;// 注销时间
	private String fwzljdCard;// 服务质量监督卡号
	private String fwzljdRepairDate;// 服务质量监督卡补办日期
	private String carid;// 车牌号
	private String cartype;// 车辆类型
	private String company;// 公司
	private String littCompany;//公司简称
	private String oldCompany;// 原公司
	private String engageConn;// 与车主关系
	private String engageTime;// 经营时间
	private String contractSrtCount;// 承包期限开始
	private String contractEndCount;// 承包期限结束
	private String approvalHours;// 受理时间
	private Date approvalHoursDate;
	private String decisionHours;// 决定时间（初领从业证日期）
	private Date decisionHoursDate;
	private String trainingUnit;
	private String status;// 状态
	private String infoPages;// 内容页数
	private String examPages;// 试卷页数
	private String invalid;// 无效
	private String sfhz;// 是否换证
	private String expReg;// 注册有效期
	private String reCyzgDate;// 补发从业证日期
	private String shzt;// 生活状态,是否死亡
	private String addtime;// 添加时间
	private byte[] photo;// 照片
	private InputStream inPhoto;
	private InputStream inTwoDim;
	private InputStream inTwoDimSupervisionCard;
	private InputStream inIdCard;
	private InputStream inId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPersonalId() {
		return personalId;
	}

	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	public String getPermitFilesId() {
		return permitFilesId;
	}

	public void setPermitFilesId(String permitFilesId) {
		this.permitFilesId = permitFilesId;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDriveCard() {
		return driveCard;
	}

	public void setDriveCard(String driveCard) {
		this.driveCard = driveCard;
	}

	public String getDriveStartDate() {
		return driveStartDate;
	}

	public void setDriveStartDate(String driveStartDate) {
		this.driveStartDate = driveStartDate;
	}

	public String getDriveType() {
		return driveType;
	}

	public void setDriveType(String driveType) {
		this.driveType = driveType;
	}

	public String getCyzgCard() {
		return cyzgCard;
	}

	public void setCyzgCard(String cyzgCard) {
		this.cyzgCard = cyzgCard;
	}

	public String getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}

	public String getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getFwzljdCard() {
		return fwzljdCard;
	}

	public void setFwzljdCard(String fwzljdCard) {
		this.fwzljdCard = fwzljdCard;
	}

	public String getFwzljdRepairDate() {
		return fwzljdRepairDate;
	}

	public void setFwzljdRepairDate(String fwzljdRepairDate) {
		this.fwzljdRepairDate = fwzljdRepairDate;
	}

	public String getCarid() {
		return carid;
	}

	public void setCarid(String carid) {
		this.carid = carid;
	}

	public String getCartype() {
		return cartype;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		if (company.equals("承德市万通客运服务有限公司")) {
			this.littCompany = "万通公司";
		} else if (company.equals("承德华夏出租汽车有限责任公司")) {
			this.littCompany = "华夏公司";
		} else if (company.equals("承德天达出租汽车有限责任公司")) {
			this.littCompany = "天达公司";
		} else if (company.equals("承德市夏利汽车出租有限公司")) {
			this.littCompany = "夏利公司";
		} else if (company.equals("承德市双滦交通出租汽车有限责任公司")) {
			this.littCompany = "双滦公司";
		} else {
			this.littCompany = company;
		}
		this.company = company;
	}

	public String getLittCompany() {
		return littCompany;
	}

	public void setLittCompany(String littCompany) {
		this.littCompany = littCompany;
	}

	public String getOldCompany() {
		return oldCompany;
	}

	public void setOldCompany(String oldCompany) {
		this.oldCompany = oldCompany;
	}

	public String getEngageConn() {
		return engageConn;
	}

	public void setEngageConn(String engageConn) {
		this.engageConn = engageConn;
	}

	public String getEngageTime() {
		return engageTime;
	}

	public void setEngageTime(String engageTime) {
		this.engageTime = engageTime;
	}

	public String getContractSrtCount() {
//		SimpleDateFormat ct = new SimpleDateFormat("yyyy-MM-dd");
//		ct.setTimeZone(TimeZone.getTimeZone("UTC"));
//		SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy年MM月dd日");
//		try {
//			this.contractSrtCount = sdfTime.format(ct.parse(this.contractSrtCount));
//		} catch (ParseException localParseException) {
//		}
		return this.contractSrtCount;
	}

	public void setContractSrtCount(String contractSrtCount) {
		this.contractSrtCount = contractSrtCount;
	}

	public String getContractEndCount() {
		return contractEndCount;
	}

	public void setContractEndCount(String contractEndCount) {
		this.contractEndCount = contractEndCount;
	}

	public String getApprovalHours() {
		return approvalHours;
	}

	public void setApprovalHours(String approvalHours) {
		this.approvalHours = DateUtil.dataToUpper(approvalHours);
		this.approvalHoursDate = DateUtil.fomatDate(approvalHours);
	}

	public Date getApprovalHoursDate() {
		return approvalHoursDate;
	}

	public void setApprovalHoursDate(Date approvalHoursDate) {
		this.approvalHoursDate = approvalHoursDate;
	}

	public String getDecisionHours() {
		return decisionHours;
	}

	public void setDecisionHours(String decisionHours) {
		if (!Tools.isEmpty(decisionHours)) {
			this.decisionHours = DateUtil.dataToUpper(decisionHours);
			this.decisionHoursDate = DateUtil.fomatDate(decisionHours);
		} else {
			this.decisionHours = decisionHours;
		}
	}

	public Date getDecisionHoursDate() {
		return decisionHoursDate;
	}

	public void setDecisionHoursDate(Date decisionHoursDate) {
		this.decisionHoursDate = decisionHoursDate;
	}

	public String getTrainingUnit() {
		return trainingUnit;
	}

	public void setTrainingUnit(String trainingUnit) {
		this.trainingUnit = trainingUnit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfoPages() {
		return infoPages;
	}

	public void setInfoPages(String infoPages) {
		this.infoPages = infoPages;
	}

	public String getExamPages() {
		return examPages;
	}

	public void setExamPages(String examPages) {
		this.examPages = examPages;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
	}

	public String getSfhz() {
		return sfhz;
	}

	public void setSfhz(String sfhz) {
		this.sfhz = sfhz;
	}

	public String getExpReg() {
		return expReg;
	}

	public void setExpReg(String expReg) {
		this.expReg = expReg;
	}

	public String getReCyzgDate() {
		return reCyzgDate;
	}

	public void setReCyzgDate(String reCyzgDate) {
		this.reCyzgDate = reCyzgDate;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public InputStream getInPhoto() {
		return inPhoto;
	}

	public void setInPhoto(InputStream inPhoto) {
		this.inPhoto = inPhoto;
	}

	public InputStream getInTwoDim() {
		return inTwoDim;
	}

	public void setInTwoDim(InputStream inTwoDim) {
		this.inTwoDim = inTwoDim;
	}

	public InputStream getInTwoDimSupervisionCard() {
		return inTwoDimSupervisionCard;
	}

	public void setInTwoDimSupervisionCard(InputStream inTwoDimSupervisionCard) {
		this.inTwoDimSupervisionCard = inTwoDimSupervisionCard;
	}

	public InputStream getInIdCard() {
		return inIdCard;
	}

	public void setInIdCard(InputStream inIdCard) {
		this.inIdCard = inIdCard;
	}

	public InputStream getInId() {
		return inId;
	}

	public void setInId(InputStream inId) {
		this.inId = inId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", phone=" + phone + ", littCompany=" + littCompany + "]";
	}

}
