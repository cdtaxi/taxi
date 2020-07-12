package cn.slkj.taxi.entity;

import java.util.Arrays;
import java.util.Date;

import cn.slkj.taxi.util.DateUtil;

/**
 * 
 * 
 * <p>
 * Title: Vehicle
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * @author wangling
 * 
 * @date 2018年10月30日
 */
public class Taxicar {
	private String id;
	private String fileNum; // 档案号
	private String opretaCertNum; // 营运证号
	private String plateNum; // 车牌号
	private String corpName; // 公司名称
	private String ownerName; // 车主姓名
	private String ownerSex; // 车主性别
	private String area; // 所属地区
	private String origOwnerName;// 原车主
	private String origPlateNum; // 原车牌号
	private String employPerson; // 操作人员
	private String color; // 颜色
	private String sign; // 车型
	private String sealRecord;// 查封记录
	private String address; // 家庭住址
	private String phoneNum; // 联系电话
	private String iDNumber; // 身份证号
	private String ownerChange;// 变更记录
	private String drvLicenseDate;// 行驶证初次登记日期
	private String vehicleInspRec;// 车辆审验记录
	private byte[] ownerNamePic; // 车主照片
	private byte[] vehiclePic; // 车辆照片
	private String tonsSeat; // 吨位
	private String carLen; // 长
	private String carWidth; // 宽
	private String carHigh; // 高
	private String frameNumber;// 车架号
	private String engineNumber;// 发动机号
	private String aDDTIME; // 操作日期
	private String checkDate; // 发证日期
	private Date checkDateFormat;
	private String transferDate;// 变更日期
	private String licenseKey;// 许可证号
	private String manageNature;// 经营性质
	private String seating;// 座位数
	private String fuelType;// 燃料类型
	private String state;// 变更状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getOpretaCertNum() {
		return opretaCertNum;
	}

	public void setOpretaCertNum(String opretaCertNum) {
		this.opretaCertNum = opretaCertNum;
	}

	public String getPlateNum() {
		return plateNum;
	}

	public void setPlateNum(String plateNum) {
		this.plateNum = plateNum;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerSex() {
		return ownerSex;
	}

	public void setOwnerSex(String ownerSex) {
		this.ownerSex = ownerSex;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOrigOwnerName() {
		return origOwnerName;
	}

	public void setOrigOwnerName(String origOwnerName) {
		this.origOwnerName = origOwnerName;
	}

	public String getOrigPlateNum() {
		return origPlateNum;
	}

	public void setOrigPlateNum(String origPlateNum) {
		this.origPlateNum = origPlateNum;
	}

	public String getEmployPerson() {
		return employPerson;
	}

	public void setEmployPerson(String employPerson) {
		this.employPerson = employPerson;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSealRecord() {
		return sealRecord;
	}

	public void setSealRecord(String sealRecord) {
		this.sealRecord = sealRecord;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getiDNumber() {
		return iDNumber;
	}

	public void setiDNumber(String iDNumber) {
		this.iDNumber = iDNumber;
	}

	public String getOwnerChange() {
		return ownerChange;
	}

	public void setOwnerChange(String ownerChange) {
		this.ownerChange = ownerChange;
	}

	public String getDrvLicenseDate() {
		return drvLicenseDate;
	}

	public void setDrvLicenseDate(String drvLicenseDate) {
		this.drvLicenseDate = drvLicenseDate;
	}

	public String getVehicleInspRec() {
		return vehicleInspRec;
	}

	public void setVehicleInspRec(String vehicleInspRec) {
		this.vehicleInspRec = vehicleInspRec;
	}

	public byte[] getOwnerNamePic() {
		return ownerNamePic;
	}

	public void setOwnerNamePic(byte[] ownerNamePic) {
		this.ownerNamePic = ownerNamePic;
	}

	public byte[] getVehiclePic() {
		return vehiclePic;
	}

	public void setVehiclePic(byte[] vehiclePic) {
		this.vehiclePic = vehiclePic;
	}

	public String getTonsSeat() {
		return tonsSeat;
	}

	public void setTonsSeat(String tonsSeat) {
		this.tonsSeat = tonsSeat;
	}

	public String getCarLen() {
		return carLen;
	}

	public void setCarLen(String carLen) {
		this.carLen = carLen;
	}

	public String getCarWidth() {
		return carWidth;
	}

	public void setCarWidth(String carWidth) {
		this.carWidth = carWidth;
	}

	public String getCarHigh() {
		return carHigh;
	}

	public void setCarHigh(String carHigh) {
		this.carHigh = carHigh;
	}

	public String getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(String frameNumber) {
		this.frameNumber = frameNumber;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

	public String getaDDTIME() {
		return aDDTIME;
	}

	public void setaDDTIME(String aDDTIME) {
		this.aDDTIME = aDDTIME;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
 
 

	public Date getCheckDateFormat() {
		return checkDateFormat;
	}

	public void setCheckDateFormat(Date checkDateFormat) {
		this.checkDateFormat = checkDateFormat;
	}

	public String getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}

	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}

	public String getManageNature() {
		return manageNature;
	}

	public void setManageNature(String manageNature) {
		this.manageNature = manageNature;
	}

	public String getSeating() {
		return seating;
	}

	public void setSeating(String seating) {
		this.seating = seating;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Taxicar [id=" + id + ", fileNum=" + fileNum + ", opretaCertNum=" + opretaCertNum + ", plateNum=" + plateNum + ", corpName=" + corpName + ", ownerName=" + ownerName + ", ownerSex="
				+ ownerSex + ", area=" + area + ", origOwnerName=" + origOwnerName + ", origPlateNum=" + origPlateNum + ", employPerson=" + employPerson + ", color=" + color + ", sign=" + sign
				+ ", sealRecord=" + sealRecord + ", address=" + address + ", phoneNum=" + phoneNum + ", iDNumber=" + iDNumber + ", ownerChange=" + ownerChange + ", drvLicenseDate=" + drvLicenseDate
				+ ", vehicleInspRec=" + vehicleInspRec + ", ownerNamePic=" + Arrays.toString(ownerNamePic) + ", vehiclePic=" + Arrays.toString(vehiclePic) + ", tonsSeat=" + tonsSeat + ", carLen="
				+ carLen + ", carWidth=" + carWidth + ", carHigh=" + carHigh + ", frameNumber=" + frameNumber + ", engineNumber=" + engineNumber + ", aDDTIME=" + aDDTIME + ", checkDate=" + checkDate
				+ ", transferDate=" + transferDate + ", licenseKey=" + licenseKey + ", manageNature=" + manageNature + ", seating=" + seating + ", fuelType=" + fuelType + ", state=" + state + "]";
	}

}
