package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;



import cn.slkj.taxi.entity.TaxicarCheck;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


@Repository
public interface TaxicarCheckMapper {

	
	public List<TaxicarCheck> getCheckList(PageData pd, PageBounds pageBounds);

	public List<TaxicarCheck> getOutCheckList(PageData pd, PageBounds pageBounds);
	
	public TaxicarCheck queryOneCheck(PageData pd);
	  
	public int saveCheck(PageData pd);

	public int editCheck(PageData pd);
	  
	public int deleteCheck(String id);
	
	public TaxicarCheck checkDateByOper(String OpretaCertNum);
	  
	public int changeStatus(PageData pd);
	
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