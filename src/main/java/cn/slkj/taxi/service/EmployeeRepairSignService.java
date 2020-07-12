/**
 * 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.EmployeeRepairSign;
import cn.slkj.taxi.mapper.EmployeeRepairSignMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * @ClassName:  EmployeeRepairSignService   
 * @Description:资格证补办  
 * @author: wangling 
 * @date:   2018年12月9日 下午5:30:33   
 *
 */
@Service
@Transactional
public class EmployeeRepairSignService {
	@Autowired
	private EmployeeRepairSignMapper mapper;

	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<EmployeeRepairSign> list(PageData pd, PageBounds pageBounds) {
		return mapper.list(pd, pageBounds);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param hashMap
	 * @return
	 */
	public EmployeeRepairSign selectOne(HashMap<String, Object> hashMap) {
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


	public int updateStatus(PageData pd) {
		return mapper.updateStatus(pd);
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
