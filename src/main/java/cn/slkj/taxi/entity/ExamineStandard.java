/**  
 * @Title:  StandardBz.java   
 * @Package cn.slkj.taxi.entity   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月19日 上午10:24:19   
 * @version V1.0 
 */
package cn.slkj.taxi.entity;

import java.util.List;

/**
 * @ClassName: ExamineStandard
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maxh
 * @date: 2018年11月19日 上午10:24:19
 * 
 */
public class ExamineStandard {
	private String id;
	private String ordinal;
	private String standard;
	private int scoring;
	private String stype;
	private String parent_id;
	private List<ExamineStandard> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public int getScoring() {
		return scoring;
	}
	public void setScoring(int scoring) {
		this.scoring = scoring;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}	
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		if(this.ordinal.trim().length()==1){
			this.parent_id = "0";
		}
		else{
		this.parent_id = this.ordinal.substring(0,this.ordinal.length()-2);
		}
	}
	public List<ExamineStandard> getChildren() {
		return children;
	}
	public void setChildren(List<ExamineStandard> children) {
		this.children = children;
	}
	
	
}
