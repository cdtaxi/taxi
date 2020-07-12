/**
 * 
 */
package cn.slkj.taxi.entity;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.Tools;



/**
 * 
 * @ClassName:  EmployeeRegister   
 * @Description:资格证注册  
 * @author: wangling 
 * @date:   2018年12月8日 上午9:29:13   
 *
 */
public class EmployeeRegister extends Employee
{
		  private String id;
		  private String cyzg_card;//从业资格证号
		  private String name;//姓名
		  private String sex;//性别
		  private String borndate;//出生日期
		  private String idcard;//身份证号
		  private String address;//地址
		  private String phone;//联系方式
		  private String drive_card;//驾驶证号
		  private String drive_start_date;//驾驶证初领日期
		  private String carid;//车号
		  private String cartype;//车型
		  private String company;//公司名称
		  private String littCompany;//公司简称
		  private String engage_conn;//与经营者关系
		  private String engage_time;//经营时间
		  private String contract_strcount;//承包起始时间
		  private String contract_endcount;//承包结束时间
		  private String passtime;//通过时间
		  private String status;//状态
		  private String addtime;//添加日期
		  private byte[] photo;//照片
		  private InputStream inIdCard;
		  private InputStream inTwoDim;
		  private InputStream inPhoto;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
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
			this.company = company;
		}
		public String getLittCompany() {
			return littCompany;
		}
		public void setLittCompany(String littCompany) {
			this.littCompany = littCompany;
		}
		
		public String getPasstime() {
			return passtime;
		}
		public void setPasstime(String passtime) {
			this.passtime = passtime;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public InputStream getInIdCard() {
			return inIdCard;
		}
		public void setInIdCard(InputStream inIdCard) {
			this.inIdCard = inIdCard;
		}
		public InputStream getInTwoDim() {
			return inTwoDim;
		}
		public void setInTwoDim(InputStream inTwoDim) {
			this.inTwoDim = inTwoDim;
		}
		public InputStream getInPhoto() {
			return inPhoto;
		}
		public void setInPhoto(InputStream inPhoto) {
			this.inPhoto = inPhoto;
		}
		public String getCyzg_card() {
			return cyzg_card;
		}
		public void setCyzg_card(String cyzg_card) {
			this.cyzg_card = cyzg_card;
		}
		public String getDrive_card() {
			return drive_card;
		}
		public void setDrive_card(String drive_card) {
			this.drive_card = drive_card;
		}
		public String getDrive_start_date() {
			return drive_start_date;
		}
		public void setDrive_start_date(String drive_start_date) {
			this.drive_start_date = drive_start_date;
		}
		public String getEngage_conn() {
			return engage_conn;
		}
		public void setEngage_conn(String engage_conn) {
			this.engage_conn = engage_conn;
		}
		public String getEngage_time() {
			return engage_time;
		}
		public void setEngage_time(String engage_time) {
			this.engage_time = engage_time;
		}
		public String getContract_strcount() {
			return contract_strcount;
		}
		public void setContract_strcount(String contract_strcount) {
			this.contract_strcount = contract_strcount;
		}
		public String getContract_endcount() {
			return contract_endcount;
		}
		public void setContract_endcount(String contract_endcount) {
			this.contract_endcount = contract_endcount;
		}
	
			
	  
	  
}
