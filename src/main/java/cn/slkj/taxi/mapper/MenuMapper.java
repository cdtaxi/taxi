package cn.slkj.taxi.mapper;

import java.util.List;
import java.util.Map;

import cn.slkj.taxi.entity.Menu;
import cn.slkj.taxi.util.PageData;

public interface MenuMapper {

	public List<Menu> listAllParentMenu(Map<String, Object> hasMap);

	public Menu queryOne(String id);
	
	public List<Menu> listAllMenu(PageData pd);

	public int save(PageData pd);

	public int edit(PageData pd);

	public int delete(String id);

	public List<Menu> getSubMenu(Map<String, Object> hasMap);

	public List<Menu> getModuleByRoleId(PageData pd);

}
