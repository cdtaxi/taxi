package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import cn.slkj.taxi.entity.EmployeeOrgan;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 从业人员dao
 */
public interface EmployeeOrganMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<EmployeeOrgan> list(HashMap<String, Object> map, PageBounds pageBounds);

	EmployeeOrgan selectOne(HashMap<String, Object> hashMap);

	int insert(PageData pd);

	int update(PageData pd);
	
	int delete(String id);
	
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
