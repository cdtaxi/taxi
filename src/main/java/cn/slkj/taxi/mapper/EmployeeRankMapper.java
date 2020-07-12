package cn.slkj.taxi.mapper;

import java.util.List;

import cn.slkj.taxi.entity.EmployeeRank;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 从业人员dao
 */
public interface EmployeeRankMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<EmployeeRank> list(PageData pd, PageBounds pageBounds);

	EmployeeRank selectOne(String id);

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
