package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.Examine;
import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.mapper.ExamineMapper;
import cn.slkj.taxi.util.PageData;

/*
 * 
 */
@Service
public class ExamineService {
	@Autowired
	private ExamineMapper examineMapper;

	public List<Examine> getExamineListByIDCard(HashMap<String, Object> map, PageBounds pageBounds) {
		return examineMapper.getExamineListByIDCard(map, pageBounds);
	}

	public List<Examine> getAllList(PageData pd, PageBounds pageBounds) {
		return examineMapper.getAllList(pd, pageBounds);
	}
	
	public Examine getOne(String id) {
		return examineMapper.getOne(id);
	}
	
	/**
	 * 
	 * @Title: save   
	 * @Description: 添加保存   
	 * @param: @param enterprise
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int save(PageData pd) {
		return examineMapper.save(pd);
	}

	/**
	 * 
	 * @Title: edit   
	 * @Description: 编辑保存   
	 * @param: @param enterprise
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int edit(PageData pd) {
		return examineMapper.edit(pd);
	}

	/**
	 * 
	 * @Title: delete   
	 * @Description: 删除   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
	public int delete(String id) {
		return examineMapper.delete(id);
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
		return examineMapper.excelList(pd);
	}
}
