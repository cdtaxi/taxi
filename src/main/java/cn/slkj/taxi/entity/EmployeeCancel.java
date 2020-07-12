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
 * @ClassName:  EmployeeCancel   
 * @Description:资格证注销   
 * @author: wangling 
 * @date:   2018年12月8日 上午11:19:18   
 *
 */
public class EmployeeCancel extends Employee
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
		  private String company;//公司名称
		  private String passtime;//通过时间
		  private String status;//状态
		  private String addtime;//添加日期
		  private Integer flag;//标识
		  private byte[] photo;//照片
		  private InputStream inIdCard;
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
		public String getAddtime() {
			return addtime;
		}
		public void setAddtime(String addtime) {
			this.addtime = addtime;
		}
		
		public Integer getFlag() {
			return flag;
		}
		public void setFlag(Integer flag) {
			this.flag = flag;
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
		
		
		
			
	  
	  
}
