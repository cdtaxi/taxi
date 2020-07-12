package cn.slkj.taxi.controller.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Enterprise;
import cn.slkj.taxi.entity.Menu;
import cn.slkj.taxi.entity.Menus;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.MenuService;
import cn.slkj.taxi.util.Const;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.Tree;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
	@Autowired
	private MenuService menuService;

	@RequestMapping("/menusPage")
	public String toMenusPage() {
		return "system/menu/menu_list";
	}

	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("system/menu/menu_edit");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/goEdit" })
	public ModelAndView goEdit() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			Menu menu = menuService.queryOne(pd.getString("id"));
			mv.setViewName("system/menu/menu_edit");
			mv.addObject("pd", menu);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@ResponseBody
	@RequestMapping(value = "/menusListByUser")
	public Map<String, Object> menusListByUser(HttpSession session) {
		PageData pd = getPageData();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		String roleId = user.getRoleId();
		Map<String, Object> hashMap = new HashMap();
		hashMap.put("pid", "1");
		hashMap.put("roleid", user.getRoleId());
		List<Menu> oneLeveList = menuService.listAllParentMenu(hashMap);
		List<Menus> oneLeve = new ArrayList<>();
		for (int i = 0; i < oneLeveList.size(); i++) {
			Menu module = oneLeveList.get(i);
			Menus menus = new Menus();
			menus.setMenuid(module.getId());
			menus.setMenuname(module.getName());
			menus.setParentMenu(module.getParent_id());
			menus.setIcon(module.getIcon());
			menus.setUrl(module.getUrl());
			oneLeve.add(menus);
		}
		System.out.println("====="+ user.getRoleId());
		List<Menu> menusList = menuService.getSubMenu(hashMap);
		List<Menus> menuslist = new ArrayList<>();
		for (int i = 0; i < menusList.size(); i++) {
			Menu module = menusList.get(i);
			Menus menus = new Menus();
			menus.setMenuid(module.getId());
			menus.setMenuname(module.getName());
			menus.setParentMenu(module.getParent_id());
			menus.setIcon(module.getIcon());
			menus.setUrl(module.getUrl());
			menuslist.add(menus);
		}
		// 拼装树形json字符串
		List<Menus> json = new TreeBuilder().buildTree(menuslist);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("oneLeve", oneLeve);
		resultMap.put("menus", json);
		JSONObject jsonString = JSONObject.fromObject(resultMap);
		// return jsonString.toString();
		return resultMap;
	}

	@ResponseBody
	@RequestMapping(value = "/listMenuTree")
	public List<Menu> listMenuTree() {
		PageData pd = getPageData();
		List<Menu> list = menuService.listAllMenu(pd);
		return makeTree(list);
	}

	@ResponseBody
	@RequestMapping(value = "/getCombotree")
	public List<Tree> getCombotree() {
		PageData pd = getPageData();
		pd.put("priority", 3);
		List<Menu> list = menuService.listAllMenu(pd);
		return toTree(list, "0");
	}

	@RequestMapping(value = "/role2Module")
	@ResponseBody
	public List<Tree> role2Module(String roleId) {
		PageData pd = getPageData();
		pd.put("priority", 3);
		pd.put("roleid", roleId);
		List<Menu> allList = menuService.listAllMenu(pd);
//		List<Menu> checkList = menuService.listAllMenu(pd);
		List<Menu> checkList = menuService.getModuleByRoleId(pd);
		return initCheckBoxTree(allList, "0", checkList);
	}

	@ResponseBody
	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public boolean add() throws Exception {
		PageData pd = new PageData();
		try {
			pd = getPageData();
			String id = pd.getString("id");
			int rti = 0;
			if (Tools.notEmpty(id)) {
				rti = menuService.edit(pd);
			} else {
				rti = menuService.save(pd);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}

	@ResponseBody
	@RequestMapping({ "/delete" })

	public boolean delete(@RequestParam String id) throws Exception {
		try {
			int i = menuService.delete(id);
			return i > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 组装树形列表 treegrid
	 * 
	 * @param list
	 * @return
	 */
	private List<Menu> makeTree(List<Menu> list) {
		List<Menu> parent = new ArrayList<Menu>();
		for (Menu e : list) {
			if (e.getParent_id().equals("0")) {
				e.setChildren(new ArrayList<Menu>(0));
				parent.add(e);
			}
		}
		list.removeAll(parent);
		makeChildren(parent, list);
		return parent;
	}

	private void makeChildren(List<Menu> parent, List<Menu> children) {
		if (children.isEmpty()) {
			return;
		}
		List<Menu> tmp = new ArrayList<Menu>();
		for (Menu c1 : parent) {
			for (Menu c2 : children) {
				c2.setChildren(new ArrayList<Menu>(0));
				if (c1.getId().equals(c2.getParent_id())) {
					c1.getChildren().add(c2);
					if (!c1.getParent_id().equals("0")) {
						c1.setState("closed");
					}
					tmp.add(c2);
				}
			}
		}
		children.removeAll(tmp);
		makeChildren(tmp, children);
	}

	private List<Tree> toTree(List<Menu> list, String code) {
		List<Tree> trees = new ArrayList<Tree>();
		for (Menu m : list) {
			Tree t = new Tree();
			t.setId(m.getId() + "");
			t.setText(m.getName());
			if (code.equals(m.getParent_id())) {
				t.setChildren(toTree(list, m.getId()));
				trees.add(t);
			}
		}
		return trees;
	}

	// 将list转换为需要的json格式
	private List<Tree> initCheckBoxTree(List<Menu> list, String id, List<Menu> list1) {
		List<Tree> trees = new ArrayList<Tree>();
		for (Menu menus : list) {
			Tree node = new Tree();
			node.setId(menus.getId());
			node.setText(menus.getName());
			// node.setIconCls(menus.getRes_icon());
			if (list1 != null) {
				// 循环判断该角色拥有的资源，如果拥有的话，设置为选择中
				for (int i = 0; i < list1.size(); i++) {
					String oid = list1.get(i).getId();
					String nid = menus.getId();
					if (oid.equals(nid)) {
						node.setChecked(true);
					}
				}
			}
			if (id.equals(menus.getParent_id())) {
				node.setChildren(initCheckBoxTree(list, node.getId(), list1));
				if (!node.getChildren().isEmpty()) {
					node.setChecked(false);
				}

				trees.add(node);
			}
		}
		return trees;
	}
}
