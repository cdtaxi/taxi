package cn.slkj.taxi.entity.cases;

public class Zfry {
	private String id;
	private String zfrxm;
	private String zfzh;
	private String zw;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getZfrxm() {
		return zfrxm;
	}
	public void setZfrxm(String zfrxm) {
		this.zfrxm = zfrxm;
	}
	public String getZfzh() {
		return zfzh;
	}
	public void setZfzh(String zfzh) {
		this.zfzh = zfzh;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	@Override
	public String toString() {
		return "Zfry [id=" + id + ", zfrxm=" + zfrxm + ", zfzh=" + zfzh + ", zw=" + zw + "]";
	}
}
