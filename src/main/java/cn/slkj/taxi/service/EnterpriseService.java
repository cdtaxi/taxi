package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.Enterprise;
import cn.slkj.taxi.mapper.EnterpriseMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


@Service
@Transactional
public class EnterpriseService {
	@Autowired
	private EnterpriseMapper mapper;

	/**
	 * 
	 * @Title: getAll   
	 * @Description: 查询公司列表  
	 * @param: @param map
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<Enterprise>      
	 * @throws
	 */
	public List<Enterprise> getAll(PageData pd, PageBounds pageBounds) {
		return mapper.queryList(pd, pageBounds);
	}

	/**
	 * 
	 * @Title: queryComList   
	 * @Description: 查询公司combox   
	 * @param: @param map
	 * @param: @return      
	 * @return: List<Enterprise>      
	 * @throws
	 */
	public List<Enterprise> queryComList(PageData pd) {
		return mapper.queryComList(pd);
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
		return mapper.save(pd);
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
		return mapper.edit(pd);
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
		return mapper.delete(id);
	}

	/**
	 * 
	 * @Title: queryOne   
	 * @Description: 主键查询   
	 * @param: @param id
	 * @param: @return      
	 * @return: Enterprise      
	 * @throws
	 */
	public Enterprise queryOne(String id) {
		return mapper.queryOne(id);
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
