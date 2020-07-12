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
 * @ClassName:  EmployeeOrgan   
 * @Description:发证机关 
 * @author: wangling 
 * @date:   2018年12月8日 上午9:28:20   
 *
 */
public class EmployeeOrgan {
	private String id;//主键
	private String parent_id; //从业资格证号
	private String type;//从业证类型
	private String first_date;//初次领证日期
	private String valid_startdate;//有效期始
	private String valid_enddate;//有效期终
	private String buban_date;//补办日期
	private String huanzheng_date;//换证日期
	private String add_date;//添加日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFirst_date() {
		return first_date;
	}
	public void setFirst_date(String first_date) {
		this.first_date = first_date;
	}
	public String getValid_startdate() {
		return valid_startdate;
	}
	public void setValid_startdate(String valid_startdate) {
		this.valid_startdate = valid_startdate;
	}
	public String getValid_enddate() {
		return valid_enddate;
	}
	public void setValid_enddate(String valid_enddate) {
		this.valid_enddate = valid_enddate;
	}
	public String getBuban_date() {
		return buban_date;
	}
	public void setBuban_date(String buban_date) {
		this.buban_date = buban_date;
	}
	public String getHuanzheng_date() {
		return huanzheng_date;
	}
	public void setHuanzheng_date(String huanzheng_date) {
		this.huanzheng_date = huanzheng_date;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}
	@Override
	public String toString() {
		return "EmployeeOrgan [id=" + id + ", parent_id=" + parent_id
				+ ", type=" + type + ", first_date=" + first_date
				+ ", valid_startdate=" + valid_startdate + ", valid_enddate="
				+ valid_enddate + ", buban_date=" + buban_date
				+ ", huanzheng_date=" + huanzheng_date + ", add_date="
				+ add_date + "]";
	}
	
	
}
