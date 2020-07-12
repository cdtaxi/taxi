/**
 * 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.CancelRegfile;
import cn.slkj.taxi.mapper.CancelRegfileMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * @ClassName:  CancelRegfileService   
 * @Description:报废注销  
 * @author: wangling 
 * @date:   2018年12月13日 上午11:27:41   
 *
 */
@Service
@Transactional
public class CancelRegfileService {
	@Autowired
	private CancelRegfileMapper mapper;

	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<CancelRegfile> listFilebyPid(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.listFilebyPid(map, pageBounds);
	}


	
	public int savePic(PageData pd) {
		return mapper.savePic(pd);
	}

	
	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @return
	 */
	public int deletePic(String id) {
		return mapper.deletePic(id);
	}

	
}
