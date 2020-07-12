package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.TaxiCarApply;
import cn.slkj.taxi.entity.Taxicar;
import cn.slkj.taxi.mapper.TaxicarMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

@Service
@Transactional
public class TaxicarService {
	@Autowired
	private TaxicarMapper mapper;

	/**
	 * 
	 * @Title: getAllList @Description: 查询列表 @param: @param hashMap @param: @param
	 * pageBounds @param: @return @return: List<Taxicar> @throws
	 */
	public List<Taxicar> getAllList(PageData pd, PageBounds pageBounds) {
		return mapper.getAllList(pd, pageBounds);
	}

	/**
	 * 
	 * @Title: save @Description: 添加保存 @param: @param
	 * taxicar @param: @return @return: int @throws
	 */
	public int save(Taxicar taxicar) {
		return mapper.save(taxicar);
	}

	/**
	 * 
	 * @Title: queryOne @Description: 主键查询 @param: @param
	 * hashMap @param: @return @return: Taxicar @throws
	 */
	public Taxicar queryOne(HashMap<String, Object> hashMap) {
		return mapper.queryOne(hashMap);
	}

	/**
	 * 
	 * @Title: edit @Description: 修改 @param: @param taxicar @param: @return @return:
	 * int @throws
	 */
	public int edit(Taxicar taxicar) {
		// TODO Auto-generated method stub
		return mapper.edit(taxicar);
	}

	/**
	 * 
	 * @Title: delete @Description: 删除 @param: @param id @param: @return @return:
	 * int @throws
	 */
	public int delete(String id) {
		return mapper.delete(id);
	}

	/**
	 * 
	 * @Title: deletes @Description: 批量删除 @param: @param
	 * ids @param: @return @return: int @throws
	 */
	public int deletes(String[] ids) {
		return mapper.deletes(ids);
	}

	/**
	 * 
	 * @Title: excelList @Description: 导出Excel @param: @param
	 * pd @param: @return @return: List @throws
	 */
	public List excelList(PageData pd) {
		return mapper.excelList(pd);
	}

	/**
	 * 
	 * @Title: getchangeAllList @Description: 查询变更记录 @param: @param
	 * pd @param: @param pageBounds @param: @return @return: List<Taxicar> @throws
	 */
	public List<Taxicar> getchangeAllList(PageData pd, PageBounds pageBounds) {
		return mapper.getchangeAllList(pd, pageBounds);
	}

	/**
	 * 
	 * @Title: saveChange @Description: b保存变更记录 @param: @param
	 * taxicar @param: @return @return: int @throws
	 */
	public int saveChange(Taxicar taxicar) {
		// TODO Auto-generated method stub
		return mapper.saveChange(taxicar);
	}

	public int change(PageData pd) {
		return mapper.change(pd);
	}

	public Taxicar queryOneChange(PageData pd) {
		return mapper.queryOneChange(pd);
	}

	public void copyChange(String id) {
		mapper.copyChange(id);
	}

	public int deleteChange(String id) {
		return mapper.delChange(id);
	}

	public int changeEdit(Taxicar taxicar) {
		return mapper.changeEdit(taxicar);
	}

	public int BatchRows(String[] ids) {
 		return mapper.BatchRows(ids);
	}

	public int updateListByChange(String[] ids) {
 		return mapper.updateListByChange(ids);
	}

	public int deleteChangeIds(String[] ids) {
 		return mapper.deleteChangeIds(ids);
	}

}
