package cn.slkj.taxi.controller.enterprise;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Enterprise;
import cn.slkj.taxi.entity.Taxicar;
import cn.slkj.taxi.service.EnterpriseService;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


@Controller
@RequestMapping(value = "/enterprise")
public class EnterpriseController  extends BaseController{

	@Autowired
	private EnterpriseService enterpriseService;

	/* 跳转页面 */
	@RequestMapping("/enterpriseListPage")
	public String enterpriseListPage() {
		return "enterprise/enterprise_list";
	}
	
	
	
	/**
	 * 
	 * @param page
	 * @param rows
	 * @param gsmc
	 * @param location
	 * @param session
	 * @return 返回easyUI datagrid 数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public EPager<Enterprise> list() throws IOException{		
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Enterprise> list = enterpriseService.getAll(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Enterprise>(pageList.getPaginator().getTotalCount(), list);
	}
	
	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("enterprise/enterprise_edit");
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
			Enterprise enterprise = enterpriseService.queryOne(pd.getString("id"));
			mv.setViewName("enterprise/enterprise_edit");
			mv.addObject("pd", enterprise);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 
	 * @Title: queryEnterpriseList   
	 * @Description: 获取公司combobox
	 * @param: @param name
	 * @param: @return      
	 * @return: List<Enterprise>      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/queryEnterpriseList")
	public List<Enterprise> queryEnterpriseList() {
		PageData pd = new PageData();
		pd = getPageData();
		return enterpriseService.queryComList(pd);
	}

	/**
	 * 获取公司/车队实体
	 * 
	 * @param classification
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOne")
	public Enterprise queryOne(String id) {
		return enterpriseService.queryOne(id);
	}
	/**
	 * 
	 * @Title: save   
	 * @Description: 保存 
	 * @param: @param enterprise
	 * @param: @return      
	 * @return: JsonResult      
	 * @throws
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public boolean save()  throws Exception{
		
		PageData pd = new PageData();
		try {
			pd = getPageData();
			int rti = 0;
			String id = pd.getString("id");
			if (Tools.notEmpty(id)) {
				rti = enterpriseService.edit(pd);
			} else {
				pd.put("id", UuidUtil.get32UUID());
				rti = enterpriseService.save(pd);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}
	

	/** 批量删除 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResult deletes(String id) {
		int i = enterpriseService.delete(id);
		try {
			if (i > 0) {
				return new JsonResult(true, "");
			} else {
				return new JsonResult(false, "操作失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(false, e.toString());
		}

	}
	@RequestMapping({"/goExcel"})
	  public ModelAndView goExcel(HttpSession session)
	  {
	    ModelAndView mv = getModelAndView();
	    PageData pd = new PageData();
	    pd = getPageData();
	   
	    try {
	    	if ((pd.getString("unitname") != null) && (!"".equalsIgnoreCase(pd.getString("unitname").trim()))) {
	    	 String unitname= URLDecoder.decode(pd.getString("unitname"), "utf-8");
	    	 pd.put("unitname", unitname);
	    	}
	    	
	      Map dataMap = new HashMap();
	      List titles = new ArrayList();

	      titles.add("编号");
	      titles.add("单位名称");
	      titles.add("联系电话");
	      titles.add("单位地址");
	      titles.add("经营范围");
	      titles.add("经营许可证号");
	      titles.add("企业代码");
	      titles.add("注册资本");
	      titles.add("经济类型");
	      titles.add("法人代表");
	      titles.add("车辆总数");
	      titles.add("从业人员总数");
	      titles.add("安全管理人员总数");
	      titles.add("企业自有车辆数");
	      titles.add("添加日期");
	      dataMap.put("titles", titles);

	      List emList = this.enterpriseService.excelList(pd);
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getString("id"));
	        vpd.put("var2", ((PageData)emList.get(i)).getString("unitname"));
	        vpd.put("var3", ((PageData)emList.get(i)).getString("phone"));
	        vpd.put("var4", ((PageData)emList.get(i)).getString("unitaddr"));
	        vpd.put("var5", ((PageData)emList.get(i)).getString("businessscope"));
	        vpd.put("var6", ((PageData)emList.get(i)).getString("businessno"));
	        vpd.put("var7", ((PageData)emList.get(i)).getString("enterprisecode"));
	        vpd.put("var8", ((PageData)emList.get(i)).getString("regcapital"));
	        vpd.put("var9", ((PageData)emList.get(i)).getString("economytype"));
	        vpd.put("var10", ((PageData)emList.get(i)).getString("legalrepresentative"));
	        vpd.put("var11", ((PageData)emList.get(i)).getString("vehiclesnum"));
	        vpd.put("var12", ((PageData)emList.get(i)).getString("employeescount"));
	        vpd.put("var13", ((PageData)emList.get(i)).getString("manageersonnel"));
	        vpd.put("var14", ((PageData)emList.get(i)).getString("carnum"));
	        vpd.put("var15", ((PageData)emList.get(i)).getString("addtime"));
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
