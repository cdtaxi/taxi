package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import cn.slkj.taxi.entity.CancelRegfile;
import cn.slkj.taxi.util.PageData;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

/**
 * 报废注销
 */
public interface CancelRegfileMapper {
	/**
	 * 查询分页数据
	 * 
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	List<CancelRegfile> listFilebyPid(HashMap<String, Object> map, PageBounds pageBounds);

	int deletePic(String id);

	int savePic(PageData pd);
	
	
}
