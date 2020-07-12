package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.slkj.taxi.entity.TaxiCarApply;
import cn.slkj.taxi.entity.Taxicar;


import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


@Repository
public interface TaxicarMapper {

	public List<Taxicar> getAllList(PageData pd, PageBounds pageBounds);

	public int save(Taxicar taxicar);

	public Taxicar queryOne(HashMap<String, Object> hashMap);

	public int edit(Taxicar taxicar);
	
	int delete(String id);
	
	int deletes(String[] ids);
	
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

	public List<Taxicar> getchangeAllList(PageData pd, PageBounds pageBounds);

	public int saveChange(Taxicar taxicar);

	public int change(PageData pd);

	public Taxicar queryOneChange(PageData pd);

	public void copyChange(String id);

	public int delChange(String id);

	public int changeEdit(Taxicar taxicar);

	public int BatchRows(String[] ids);

	public int updateListByChange(String[] ids);

	public int  deleteChangeIds(String[] ids);

	
}