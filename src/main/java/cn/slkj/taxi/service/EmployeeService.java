/**
 * 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.mapper.EmployeeMapper;
import cn.slkj.taxi.util.PageData;

/**
 * @author maxuh
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeMapper mapper;

	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<Employee> listPage(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.listPage(map, pageBounds);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param hashMap
	 * @return
	 */
	public Employee selectOne(HashMap<String, Object> hashMap) {
		return mapper.selectOne(hashMap);
	}

	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(String id) {
		return mapper.deleteById(id);
	}

	/**
	 * 查询报考人员
	 * @Title: slistPage   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param map
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws
	 */
	public List<Employee> slistPage(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.slistPage(map, pageBounds);
	}

	/**
	 * 保存报考人员
	 * @Title: insertSelective   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param employee
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int insertSelective(PageData pd) {
		return mapper.insertSelective(pd);
	}

	public int update(PageData pd) {
		return mapper.update(pd);
	}
	
	public int updateByIDCard(PageData pd) {
		return mapper.updateByIDCard(pd);
	}
	/**
	 * 
	 * @Title: listDie   
	 * @Description: 死亡库 
	 * @param: @param map
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws
	 */
	public List<Employee> listDie(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.listDie(map, pageBounds);
	}
	/**
	 * 
	 * @Title: listDie   
	 * @Description: 超龄库 
	 * @param: @param map
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<Employee>      
	 * @throws
	 */
	public List<Employee> listOldAge(HashMap<String, Object> map, PageBounds pageBounds) {
		return mapper.listOldAge(map, pageBounds);
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
