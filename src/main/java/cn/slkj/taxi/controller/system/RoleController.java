package cn.slkj.taxi.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.service.RoleService;
import cn.slkj.taxi.util.PageData;

/**
 * 
 * @ClassName: RoleController
 * @Description: 角色管理控制类
 * @author maxh
 *
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
	@Resource
	private RoleService roleService;

	/** 跳转页面 **/
	// 角色管理页面
	@RequestMapping("/roleListPage")
	public String roleListPage() {
		return "system/role/role_List";
	}

	@ResponseBody
	@RequestMapping("/list")
	public List<Role> queryAll(HttpServletRequest request) throws Exception {
		PageData pd = new PageData();
		pd = getPageData();

		List<Role> list = roleService.getList(pd);
		return list;
	}

	@ResponseBody
	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public boolean add() throws Exception {
		PageData pd = new PageData();
		try {
			pd = getPageData();
			String name = pd.getString("name");
			Role role = new Role();
			role.setName(name);
			int i = roleService.saveRole(role);
			return i > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}

	@RequestMapping({ "/delete" })
	@ResponseBody
	public boolean deleteRole(@RequestParam String id) throws Exception {
		try {
			Role role = new Role();
			role.setId(id);
			int i = roleService.deleteRole(role);
			return i > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}
	/**
	 * 保存角色和资源之间的关系
	 * 
	 * @param member
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveRoleRes", method = RequestMethod.POST)
	private boolean intoRole(@RequestParam(required = false, defaultValue = "") String roleid, @RequestParam(value = "ids[]") String[] ids) {
		if (StringUtils.isNotBlank(roleid)) {
			roleService.deleteRoleRes(roleid, ids);
			int i = roleService.saveRoleRes(roleid, ids);
			if (i != -1) {
				return true;
			}
		}
		this.logger.error("{}，角色id为空");
		return false;
	}
}
