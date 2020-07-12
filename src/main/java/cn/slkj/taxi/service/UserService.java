/**  
 * @Title:  UserService.java   
 * @Package cn.slkj.taxi.service   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月8日 下午3:01:07   
 * @version V1.0 
 */
package cn.slkj.taxi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.mapper.UserMapper;
import cn.slkj.taxi.util.PageData;

/**
 * @ClassName: UserService
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maxuh
 * 
 */
@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	// public List<User> selectAllUser(HashMap<String, Object> hashMap) {
	// return userMapper.selectAllUser(hashMap);
	// }
	//
	// public User selectOne(HashMap<String, Object> hashMap) {
	// return userMapper.queryOne(hashMap);
	// }

	public User getUserByNameAndPwd(PageData pd) {
		return userMapper.getUserByNameAndPwd(pd);
	}

	public List<User> getAllUsers(PageData pd, PageBounds pageBounds) {
		return userMapper.getAllUsers(pd, pageBounds);
	}

	public int saveUser(PageData pd) {
		return userMapper.saveUser(pd);
	}

	public int deleteUser(String id) {
		return userMapper.deleteUser(id);
	}

	public int editUser(PageData pd) {
		return userMapper.editUser(pd);
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
		return userMapper.excelList(pd);
	}
}
