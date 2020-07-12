package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.slkj.taxi.entity.TaxicarCheck;
import cn.slkj.taxi.mapper.TaxicarCheckMapper;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;


@Service
@Transactional
public class TaxicarCheckService {
	@Autowired
	private TaxicarCheckMapper mapper;

	
	
	
	/**
	 * 
	 * @Title: getCheckList   
	 * @Description: 查询年审列表   
	 * @param: @param hashMap
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<TaxicarCheck>      
	 * @throws
	 */
	public List<TaxicarCheck> getCheckList(PageData pd, PageBounds pageBounds) {
		return mapper.getCheckList(pd, pageBounds);
	}
	
	/**
	 * 
	 * @Title: getOutCheckList   
	 * @Description: 过期审批列表   
	 * @param: @param hashMap
	 * @param: @param pageBounds
	 * @param: @return      
	 * @return: List<TaxicarCheck>      
	 * @throws
	 */
	public List<TaxicarCheck> getOutCheckList(PageData pd, PageBounds pageBounds) {
		return mapper.getOutCheckList(pd, pageBounds);
	}
	/**
	 * 年审主键查询
	 */
  public TaxicarCheck queryOneCheck(PageData pd)
  {
    return mapper.queryOneCheck(pd);
  }
	/**
	 * 
	 * @Title: saveCheck   
	 * @Description: 年审添加保存   
	 * @param: @param taxicarCheck
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
  public int saveCheck(PageData pd)
  {
    return mapper.saveCheck(pd);
  }
	/**
	 * 
	 * @Title: editCheck   
	 * @Description: 年审编辑   
	 * @param: @param taxicarCheck
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
  public int editCheck(PageData pd)
  {
    return mapper.editCheck(pd);
  }
	/**
	 * 
	 * @Title: deleteCheck   
	 * @Description: 年审删除   
	 * @param: @param id
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
  public int deleteCheck(String id)
  {
    return mapper.deleteCheck(id);
  }
	/**
	 * 
	 * @Title: checkDateByOper   
	 * @Description: 营运证号查询   
	 * @param: @param OpretaCertNum
	 * @param: @return      
	 * @return: TaxicarCheck      
	 * @throws
	 */
  public TaxicarCheck checkDateByOper(String OpretaCertNum)
  {
    return mapper.checkDateByOper(OpretaCertNum);
  }

  
	/**
	 * 
	 * @Title: changeStatus   
	 * @Description: 修改审核状态   
	 * @param: @param hashMap
	 * @param: @return      
	 * @return: int      
	 * @throws
	 */
  public int changeStatus(PageData pd)
  {
    return mapper.changeStatus(pd);
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
