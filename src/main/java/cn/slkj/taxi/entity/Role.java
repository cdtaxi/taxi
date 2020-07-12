package cn.slkj.taxi.entity;

/**
 * @ClassName: Role
 * @Description: 角色
 * @author maxh
 * 
 */
public class Role {
	private String id;// uuid
	private String name;// 角色名称
	private String rights;
	private String parent_id;
	private String add_qx;
	private String del_qx;
	private String edit_qx;
	private String cha_qx;
	private String qx_id;

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

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getAdd_qx() {
		return add_qx;
	}

	public void setAdd_qx(String add_qx) {
		this.add_qx = add_qx;
	}

	public String getDel_qx() {
		return del_qx;
	}

	public void setDel_qx(String del_qx) {
		this.del_qx = del_qx;
	}

	public String getEdit_qx() {
		return edit_qx;
	}

	public void setEdit_qx(String edit_qx) {
		this.edit_qx = edit_qx;
	}

	public String getCha_qx() {
		return cha_qx;
	}

	public void setCha_qx(String cha_qx) {
		this.cha_qx = cha_qx;
	}

	public String getQx_id() {
		return qx_id;
	}

	public void setQx_id(String qx_id) {
		this.qx_id = qx_id;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", rights=" + rights + ", parent_id=" + parent_id + ", add_qx=" + add_qx + ", del_qx=" + del_qx + ", edit_qx=" + edit_qx + ", cha_qx=" + cha_qx
				+ ", qx_id=" + qx_id + "]";
	}
}
