/**
 * 
 */
package cn.slkj.taxi.util;

/**
 * @author maxh 返回统一json格式
 * 
 */
public class JsonResult {

	private boolean success;
	private String msg;

	private Object  attribute;
	
	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}

	public JsonResult() {
	}
	public JsonResult(boolean success, String msg  ) {
		this.success = success;
		this.msg = msg;
	}
	public JsonResult(boolean success, String msg,Object obj) {
		this.success = success;
		this.msg = msg;
		this.attribute =obj;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "JsonResult [success=" + success + ", msg=" + msg + "]";
	}
}
