/**  
 * @Title:  RoleMapper.java   
 * @Package cn.slkj.taxi.mapper   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月10日 下午2:14:25   
 * @version V1.0 
 */
package cn.slkj.taxi.mapper;

import java.util.HashMap;
import java.util.List;

import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.util.PageData;

/**
 * @ClassName: RoleMapper
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maxuh
 * @date: 2018年11月10日 下午2:14:25
 * 
 */
public interface RoleMapper {

	public List<Role> getList(PageData pd);

	public int saveRole(Role role);

	public int deleteRole(Role role);

	public int deleteRoleRes(HashMap<String, Object> map);

	public int saveRoleRes(HashMap<String, Object> map);

}
