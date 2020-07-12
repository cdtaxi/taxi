package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import cn.slkj.taxi.entity.EmployeeRepairSign;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 公司更换
 */
public interface EmployeeRepairSignMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<EmployeeRepairSign> list(PageData pd, PageBounds pageBounds);

	EmployeeRepairSign selectOne(HashMap<String, Object> hashMap);

	int delete(String id);

	int insert(PageData pd);

	int update(PageData pd);
	
	int updateStatus(PageData pd);
	
	
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
