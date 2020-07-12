/**
 * 
 */
package cn.slkj.taxi.entity;

import java.io.InputStream;
import java.util.Date;

import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.Tools;


/**
 * 
 * @ClassName:  ChangeCompany   
 * @Description:变更公司   
 * @author: wangling 
 * @date:   2018年12月9日 上午8:53:46   
 *
 */
public class ChangeCompany {
	private String id;//主键
	private String cyzg_card;//从业资格证号
	private String name;//姓名
	private String old_company;//原公司名称
	private String new_company;//现公司名称
	private String status;//状态
	private String addtime;//添加时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCyzg_card() {
		return cyzg_card;
	}
	public void setCyzg_card(String cyzg_card) {
		this.cyzg_card = cyzg_card;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOld_company() {
		return old_company;
	}
	public void setOld_company(String old_company) {
		this.old_company = old_company;
	}
	public String getNew_company() {
		return new_company;
	}
	public void setNew_company(String new_company) {
		this.new_company = new_company;
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
	@Override
	public String toString() {
		return "ChangeCompany [id=" + id + ", cyzg_card=" + cyzg_card
				+ ", name=" + name + ", old_company=" + old_company
				+ ", new_company=" + new_company + ", status=" + status
				+ ", addtime=" + addtime + "]";
	}
	
	
	
}
