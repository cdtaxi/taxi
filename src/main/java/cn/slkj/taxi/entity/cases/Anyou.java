package cn.slkj.taxi.entity.cases;

public class Anyou {
	private String id;
	private String anyou;// 案由
	private String aybh;// 案由编号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnyou() {
		return anyou;
	}

	public void setAnyou(String anyou) {
		this.anyou = anyou;
	}

	public String getAybh() {
		return aybh;
	}

	public void setAybh(String aybh) {
		this.aybh = aybh;
	}

	@Override
	public String toString() {
		return "Anyou [id=" + id + ", anyou=" + anyou + ", aybh=" + aybh + "]";
	}

}
