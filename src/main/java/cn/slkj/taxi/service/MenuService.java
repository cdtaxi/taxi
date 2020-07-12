package cn.slkj.taxi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.slkj.taxi.entity.Menu;
import cn.slkj.taxi.mapper.MenuMapper;
import cn.slkj.taxi.util.PageData;

/*
 * 
 */
@Service
public class MenuService {
	@Autowired
	private MenuMapper menuMapper;

	public List<Menu> listAllParentMenu(Map<String, Object> hasMap) {
		return menuMapper.listAllParentMenu(hasMap);
	}

	public Menu queryOne(String id) {
		return menuMapper.queryOne(id);
	}
	
	public List<Menu> listAllMenu(PageData pd) {
		return menuMapper.listAllMenu(pd);
	}

	public int delete(String id) {
		return menuMapper.delete(id);
	}

	public int edit(PageData pd) {
		return menuMapper.edit(pd);
	}

	public int save(PageData pd) {
		return menuMapper.save(pd);
	}

	public List<Menu> getSubMenu(Map<String, Object> hasMap) {
		return menuMapper.getSubMenu(hasMap);
	}

	public List<Menu> getModuleByRoleId(PageData pd) {
		return menuMapper.getModuleByRoleId(pd);
	}
}
