package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.Examine;
import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.util.PageData;

/**
 *  
 */
public interface ExamineMapper {
	 
	List<Examine> getExamineListByIDCard(HashMap<String, Object> map, PageBounds pageBounds);
	List<Examine> getAllList(PageData pd, PageBounds pageBounds);
	Examine getOne(String id);
	int save(PageData pd);
	int edit(PageData pd);
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
