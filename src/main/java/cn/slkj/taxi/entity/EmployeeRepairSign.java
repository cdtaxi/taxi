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
 * @ClassName:  EmployeeRepairSign   
 * @Description:资格证补办   
 * @author: wangling 
 * @date:   2018年12月8日 上午11:35:57   
 *
 */
public class EmployeeRepairSign
{
	  private String id;
	  private String name;//姓名
	  private String sex;//性别
	  private String borndate;//出生日期
	  private String idcard;//身份证号
	  private String address;//住址
	  private String phone;//联系方式
	  private String drive_type;//驾驶证类型
	  private String drive_start_date;//驾驶证初次登记日期
	  private String company;//公司
	  private String carid;//车号
	  private String old_sign_id;//原资格证号
	  private String first_sign_date;//初次注册日期
	  private String passtime;//通过时间
	  private String status;
	  private Date addtime;
	  private InputStream inIdCard;
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
		
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
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
		
	
		public InputStream getInIdCard() {
			return inIdCard;
		}
		public void setInIdCard(InputStream inIdCard) {
			this.inIdCard = inIdCard;
		}
		public String getDrive_type() {
			return drive_type;
		}
		public void setDrive_type(String drive_type) {
			this.drive_type = drive_type;
		}
		public String getDrive_start_date() {
			return drive_start_date;
		}
		public void setDrive_start_date(String drive_start_date) {
			this.drive_start_date = drive_start_date;
		}
		public String getOld_sign_id() {
			return old_sign_id;
		}
		public void setOld_sign_id(String old_sign_id) {
			this.old_sign_id = old_sign_id;
		}
		public String getFirst_sign_date() {
			return first_sign_date;
		}
		public void setFirst_sign_date(String first_sign_date) {
			this.first_sign_date = first_sign_date;
		}
		public Date getAddtime() {
			return addtime;
		}
		public void setAddtime(Date addtime) {
			this.addtime = addtime;
		}
	
		
		
			
	  
	  
}
