/**  
 * @Title:  Examine.java   
 * @Package cn.slkj.taxi.entity   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月22日 上午9:36:25   
 * @version V1.0 
 */
package cn.slkj.taxi.entity;

/**   
 * @ClassName:  Examine   
 * @Description:考核 
 * @author: maxh 
 * @date:   2018年11月22日 上午9:36:25   
 *     
 */
public class Examine {

	private int id;
	private String empId;//人员身份证号
	private String ordinalId;//扣分项
	private int scoring;//扣分分数
	private int residueScore;//剩余分数
	private String examineTime;//时间
	private String remark;
	private String ordinal;//扣分项
	private String name;//人员姓名
	private String cyzgCard;//从业资格证号
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getOrdinalId() {
		return ordinalId;
	}
	public void setOrdinalId(String ordinalId) {
		this.ordinalId = ordinalId;
	}
	public String getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}
	public int getScoring() {
		return scoring;
	}
	public void setScoring(int scoring) {
		this.scoring = scoring;
	}
	public int getResidueScore() {
		return residueScore;
	}
	public void setResidueScore(int residueScore) {
		this.residueScore = residueScore;
	}
	public String getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCyzgCard() {
		return cyzgCard;
	}
	public void setCyzgCard(String cyzgCard) {
		this.cyzgCard = cyzgCard;
	}
	
	
}
