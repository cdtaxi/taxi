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
 * @ClassName:  CancelRegfile   
 * @Description:报废注销 
 * @author: wangling 
 * @date:   2018年12月13日 上午11:21:39   
 *
 */
public class CancelRegfile {
	private String id;//主键
	private String pid;//人员资格证号
	private String path;//报废单路径
	private String createtime;//生成时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
	
	
	
	
}
