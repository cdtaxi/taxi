package cn.slkj.taxi.controller.system;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.UserService;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;

/**
 * 
 * @ClassName: UserController
 * @Description: 用户信息
 * @author maxh
 *
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes("userSession")
public class UserController extends BaseController {
	@Resource
	private UserService userService;

	/* 跳转页面 */
	@RequestMapping("/userListPage")
	public String userListPage() {
		return "system/user/user_List";
	}

	@ResponseBody
	@RequestMapping(value = "list")
	public EPager<User> getAllParam() throws IOException {
		String sortString = "";
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");

		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<User> list = userService.getAllUsers(pd, pageBounds);
		PageList<User> pageList = (PageList<User>) list;
		return new EPager<User>(pageList.getPaginator().getTotalCount(), list);
	}

	@ResponseBody
	@RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
	public boolean add() throws Exception {
		PageData pd = new PageData();
		try {
			pd = getPageData();
			int rti = 0;
			String id = pd.getString("id");
			if (Tools.notEmpty(id)) {
			
				rti = userService.editUser(pd);
			} else {
				pd.put("password", "000000");
				rti = userService.saveUser(pd);
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
			int i = userService.deleteUser(id);
			return i > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}
	
	@RequestMapping({"/goExcel"})
	  public ModelAndView goExcel(HttpSession session)
	  {
	    ModelAndView mv = getModelAndView();
	    PageData pd = new PageData();
	    pd = getPageData();
	   
	    try {
	    	
	    	if ((pd.getString("name") != null) && (!"".equalsIgnoreCase(pd.getString("name").trim()))) {
		    	 String name= URLDecoder.decode(pd.getString("name"), "utf-8");
		    	 pd.put("name", name);
		    	}
	    	
			
	      Map dataMap = new HashMap();
	      List titles = new ArrayList();

	      titles.add("编号");
	      titles.add("姓名");
	      titles.add("邮箱");
	      titles.add("真实姓名");
	      titles.add("性别");
	      titles.add("联系电话");	
	      titles.add("地址");	
	      titles.add("所属部门");	
	      titles.add("注册日期");	
	      titles.add("最后登录日期");	
	      titles.add("备注");	
	      titles.add("状态");	      
	      dataMap.put("titles", titles);

	      List emList = this.userService.excelList(pd);System.out.println("到这里了？？");
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getIntegr("id").toString());System.out.println("还是到这里了？？");
	        vpd.put("var2", ((PageData)emList.get(i)).getString("username"));System.out.println("还是到这里了？？");
	        vpd.put("var3", ((PageData)emList.get(i)).getString("email"));
	        vpd.put("var4", ((PageData)emList.get(i)).getString("realname"));
	        //vpd.put("var5", ((PageData)emList.get(i)).getString("sex"));
	        if (((PageData)emList.get(i)).getString("sex") != null) {
		          if (((PageData)emList.get(i)).getString("sex").equals("0"))
		            vpd.put("var5", "男");
		          else if (((PageData)emList.get(i)).getString("sex").equals("1"))
		            vpd.put("var5", "女");
		          else
		            vpd.put("var5", "不详");
		        }
		        else {
		          vpd.put("var5", "不详");
		        }
	        vpd.put("var6", ((PageData)emList.get(i)).getString("phone"));
	        vpd.put("var7", ((PageData)emList.get(i)).getString("address"));
	        vpd.put("var8", ((PageData)emList.get(i)).getString("departName"));
	        vpd.put("var9", ((PageData)emList.get(i)).getString("create_time"));
	        vpd.put("var10", ((PageData)emList.get(i)).getString("lastTime"));
	        vpd.put("var11", ((PageData)emList.get(i)).getString("remark"));
	        if (((PageData)emList.get(i)).getString("status") != null) {
		          if (((PageData)emList.get(i)).getString("status").equals("enabled"))
		            vpd.put("var12", "启用");
		          else 
		            vpd.put("var12", "禁用");
		          
		        }
	        varList.add(vpd);
	      }

	      dataMap.put("varList", varList);

	      ObjectExcelView erv = new ObjectExcelView();

	      mv = new ModelAndView(erv, dataMap);
	    } catch (Exception e) {System.out.println(e.toString());
	      this.logger.error(e.toString(), e);
	    }
	    return mv;
	  }
}
