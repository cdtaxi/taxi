package cn.slkj.taxi.mapper;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.util.PageData;

public interface UserMapper {

	User getUserByNameAndPwd(PageData pd);

	List<User> getAllUsers(PageData pd, PageBounds pageBounds);

	int saveUser(PageData pd);

	int deleteUser(String id);

	int editUser(PageData pd);
	
	/**
	 * 
	 * @Title: excelList   
	 * @Description: 导出Excel  
	 * @param: @param pd
	 * @param: @return      
	 * @return: List      
	 * @throws
	 */
	List excelList(PageData pd);
}
