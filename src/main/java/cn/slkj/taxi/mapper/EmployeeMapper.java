package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.util.PageData;

/**
 * 从业人员dao
 */
public interface EmployeeMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<Employee> listPage(HashMap<String, Object> map, PageBounds pageBounds);

	Employee selectOne(HashMap<String, Object> hashMap);

	int deleteById(String id);

	List<Employee> slistPage(HashMap<String, Object> map, PageBounds pageBounds);

	int insertSelective(PageData pd);

	int update(PageData pd);
	
	int updateByIDCard(PageData pd);
	
	/**
	 * 死亡库
	 */
	List<Employee> listDie(HashMap<String, Object> map, PageBounds pageBounds);
	/**
	 * 超龄库
	 */
	List<Employee> listOldAge(HashMap<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 
	 * @Title: excelList   
	 * @Description: 导出Excel  
	 * @param: @param pd
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	List excelList(PageData pd);
}
