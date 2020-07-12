/**  
 * @Title:  RoleService.java   
 * @Package cn.slkj.taxi.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月10日 下午2:13:22   
 * @version V1.0 
 */
package cn.slkj.taxi.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.mapper.RoleMapper;
import cn.slkj.taxi.util.PageData;

/**
 * @ClassName: RoleService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maxuh
 * @date: 2018年11月10日 下午2:13:22
 * 
 */
@Service
public class RoleService {
	@Autowired
	private RoleMapper roleMapper;

	public List<Role> getList(PageData pd) {
		return roleMapper.getList(pd);
	}

	public int saveRole(Role role) {
		return roleMapper.saveRole(role);
	}

	public int deleteRole(Role role) {
		return roleMapper.deleteRole(role);
	}

	public int deleteRoleRes(String roleid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("ids", ids);
		return roleMapper.deleteRoleRes(map);
	}

	public int saveRoleRes(String roleid, String[] ids) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleid", roleid);
		map.put("ids", ids);
		return roleMapper.saveRoleRes(map);
	}
}
