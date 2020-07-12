/**
 * 
 */
package cn.slkj.taxi.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.UserService;
import cn.slkj.taxi.util.AppUtil;
import cn.slkj.taxi.util.Const;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;

/**
 * @author maxuh
 *
 */

@Controller
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping({ "/login_toLogin" })
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = getModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		pd.put("SYSNAME", Tools.readTxtFile("admin/config/SYSNAME.txt"));
		mv.setViewName("login");
		mv.addObject("pd", pd);
		return mv;
	}

	// 总台账跳转页面
	@RequestMapping("/index")
	public ModelAndView login_index(HttpSession session) {
		ModelAndView mv = getModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if (user != null) {
			mv.addObject("user", user);
			mv.setViewName("index");
		} else {
			mv.setViewName("login");
		}
		return mv;
	}

	@ResponseBody
	@RequestMapping(value = { "/login_login" }, produces = { "application/json;charset=UTF-8" })
	public Object login(HttpSession session) throws Exception {
		Map<String, String> map = new HashMap();
		PageData pd = new PageData();
		pd = getPageData();
		String errInfo = "";
		String[] KEYDATA = pd.getString("KEYDATA").split(",");
		if ((KEYDATA != null) && (KEYDATA.length == 3)) {
			String username = KEYDATA[0];
			String password = KEYDATA[1];
			pd.put("username", username);
			pd.put("password", password);
			User user = this.userService.getUserByNameAndPwd(pd);
			if (user != null) {
				session.removeAttribute(Const.SESSION_USER);
				session.setAttribute(Const.SESSION_USER, user);
			} else {
				errInfo = "usererror";
			}
		} else {
			errInfo = "error";
		}
		if (Tools.isEmpty(errInfo)) {
			errInfo = "success";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	@RequestMapping({ "/logout" })
	public ModelAndView logout(HttpSession session) {

		ModelAndView mv = getModelAndView();
		mv.setViewName("login");
		session.invalidate();
		return mv;
	}
}
