package cn.slkj.taxi.util;

import java.util.List;

public class Tree {
	private String id;// 节点的ID
	private String text;// 节点显示的文字
	private String state = "open";// 节点状态，有两个值 'open' or 'closed', 默认为'open'.
									// 当为‘closed’时说明此节点下有子节点否则此节点为叶子节点
	private boolean checked;// Indicate whether the node is checked selected.
	private Object attributes;// 节点中其他属性的集合
	private List<Tree> children;// 子节点集合

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

}
