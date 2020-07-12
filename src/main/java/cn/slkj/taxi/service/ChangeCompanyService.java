/**
 * 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.ChangeCompany;
import cn.slkj.taxi.mapper.ChangeCompanyMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 
 * @ClassName:  ChangeCompanyService   
 * @Description:公司变更 
 * @author: wangling 
 * @date:   2018年12月9日 下午5:24:42   
 *
 */
@Service
@Transactional
public class ChangeCompanyService {
	@Autowired
	private ChangeCompanyMapper mapper;

	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<ChangeCompany> list(PageData pd, PageBounds pageBounds) {
		return mapper.list(pd, pageBounds);
	}

	/**
	 * 查询一条记录
	 * 
	 * @param hashMap
	 * @return
	 */
	public ChangeCompany selectOne(HashMap<String, Object> hashMap) {
		return mapper.selectOne(hashMap);
	}

	
	public int insert(PageData pd) {
		return mapper.insert(pd);
	}

	public int update(PageData pd) {
		return mapper.update(pd);
	}
	
	/**
	 * 根据id删除一条记录
	 * 
	 * @param id
	 * @return
	 */
	public int delete(String id) {
		return mapper.delete(id);
	}


	public int updateStatus(PageData pd) {
		return mapper.updateStatus(pd);
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
