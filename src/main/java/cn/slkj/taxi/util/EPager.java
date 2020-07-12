/**  
 *
 */
package cn.slkj.taxi.util;

import java.util.List;

public class EPager<T> {
	/** 总记录数 */
	private int total;

	/** 获得分页内容 */
	private List<T> rows;
	private List<T> footer;
	public EPager() {

	}

	public EPager(int total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}
	public EPager(int total, List<T> rows,List<T> footer) {
		this.total = total;
		this.rows = rows;
		this.footer=footer;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<T> getFooter() {
		return footer;
	}

	public void setFooter(List<T> footer) {
		this.footer = footer;
	}

	@Override
	public String toString() {
		return "ResultPager [total=" + total + ", rows=" + rows + "]";
	}

}
