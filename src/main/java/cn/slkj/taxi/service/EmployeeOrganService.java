/**
 * 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.EmployeeOrgan;
import cn.slkj.taxi.mapper.EmployeeOrganMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * @author maxuh
 *
 */
@Service
@Transactional
public class EmployeeOrganService {
	@Autowired
	private EmployeeOrganMapper mapper;

	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<EmployeeOrgan> list(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.list(map, pageBounds);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param hashMap
	 * @return
	 */
	public EmployeeOrgan selectOne(HashMap<String, Object> hashMap) {
		return mapper.selectOne(hashMap);
	}

	
	public int insert(PageData pd) {
		return mapper.insert(pd);
	}

	public int update(PageData pd) {
		return mapper.update(pd);
	}
	
	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id) {
		return mapper.delete(id);
	}

	/**
	 * 
	 * @Title: excelList   
	 * @Description: 导出Excel 
	 * @param: @param pd
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	public List excelList(PageData pd) {
		return mapper.excelList(pd);
	}
}
