package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import cn.slkj.taxi.entity.EmployeeContinueRegister;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 从业资格证注册
 */
public interface EmployeeContinueRegisterMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<EmployeeContinueRegister> list(PageData pd, PageBounds pageBounds);

	EmployeeContinueRegister selectById(HashMap<String, Object> hashMap);

	int insert(PageData pd);

	int update(PageData pd);
	
	int delete(String id);
	
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
