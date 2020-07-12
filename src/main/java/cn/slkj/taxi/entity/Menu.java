/**
 * 
 */
package cn.slkj.taxi.entity;

import java.util.List;

/**
 * 菜单
 * 
 * @author maxh
 *
 */
public class Menu {

	private String id;
	private String name;
	private String url;
	private String parent_id;
	private int priority;
	private String icon;
	private String description;
	private int sort;
	private String state;
	private List<Menu> children;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Menu> getChildren() {
		return children;
	}
	public void setChildren(List<Menu> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", name=" + name + ", parent_id=" + parent_id + "]";
	}
	
}
