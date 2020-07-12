/**
 * 
 */
package cn.slkj.taxi.controller.examine;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.Enterprise;
import cn.slkj.taxi.entity.Examine;
import cn.slkj.taxi.entity.ExamineStandard;
import cn.slkj.taxi.service.EmployeeService;
import cn.slkj.taxi.service.ExamineService;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;

/**
 * 
 * 诚信考核
 * 
 * @author maxuh
 *
 */

@Controller
@RequestMapping({ "/examine" })
public class ExamineController extends BaseController {

	@Autowired
	private ExamineService examineService;
	@Autowired
	private EmployeeService employeeService;
	
	/* 跳转页面 */
	@RequestMapping("/listPage")
	public String listPage() {
		return "examine/examine_list";
	}
	
	/**
	 * 
	 * @param page考核记录页面list
	 * @param rows
	 * @param gsmc
	 * @param location
	 * @param session
	 * @return 返回easyUI datagrid 数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list")
	public EPager<Examine> list() throws IOException{		
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "examineTime.DESC";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Examine> list = examineService.getAllList(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Examine>(pageList.getPaginator().getTotalCount(), list);
	}
	//考核登记页面list
	@ResponseBody
	@RequestMapping(value = "/listByIdCard")
	public EPager<Examine> listByIdCard() throws IOException{		
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "examineTime.DESC";
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Examine> list = examineService.getExamineListByIDCard(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Examine>(pageList.getPaginator().getTotalCount(), list);
	}
	//考核查询页面list
	@RequestMapping("/listOne")
	public ModelAndView listOne() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			if ((pd.getString("idcard") != null) && (!"".equalsIgnoreCase(pd.getString("idcard").trim()))) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("idcard", pd.getString("idcard"));
				Employee employee = this.employeeService.selectOne(hashMap);
				mv.addObject("employee", employee);
				
				Integer rows = 100;
				Integer page = 1;
				String sortString = "";// 如果你想排序的话逗号分隔可以排序多列
				/*HashMap<String, Object> map = new HashMap<String, Object>();*/
				PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
				List<Examine> list = examineService.getExamineListByIDCard(hashMap, pageBounds);
				
				//int fractionTotal = 100;
				int syFraction=100;
				for (int i = 0; i < list.size(); i++) {
					Examine e = list.get(i);
					//判断加分减分项目
					if(e.getOrdinal().contains("6-")) {
						syFraction = syFraction + e.getScoring();
					}else {
						syFraction = syFraction - e.getScoring();
					}
					
				}
				//剩余分数
				mv.addObject("syFraction", syFraction);			
				
				mv.addObject("staList", list);
			}
			mv.addObject("pd", pd);
			mv.setViewName("examine/examine_list_one");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	//考核登记页面
	@RequestMapping("/examineAdd")
	public ModelAndView examineAdd() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			if ((pd.getString("idcard") != null) && (!"".equalsIgnoreCase(pd.getString("idcard").trim()))) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("idcard", pd.getString("idcard"));
				Employee employee = this.employeeService.selectOne(hashMap);
				mv.addObject("employee", employee);
				
				Integer rows = 100;
				Integer page = 1;
				String sortString = "";// 如果你想排序的话逗号分隔可以排序多列
				/*HashMap<String, Object> map = new HashMap<String, Object>();*/
				PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
				List<Examine> list = examineService.getExamineListByIDCard(hashMap, pageBounds);
				
				//int fractionTotal = 100;
				int syFraction=100;
				for (int i = 0; i < list.size(); i++) {
					Examine e = list.get(i);
					//判断加分减分项目
					if(e.getOrdinal().contains("6-")) {
						syFraction = syFraction + e.getScoring();
					}else {
						syFraction = syFraction - e.getScoring();
					}
					
				}
				//剩余分数
				mv.addObject("syFraction", syFraction);			
				
				//mv.addObject("staList", list);
			}
			mv.addObject("pd", pd);
			mv.setViewName("examine/examineAdd");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {			
			mv.setViewName("examine/examine_edit");
			mv.addObject("msg", "add");
			mv.addObject("empId", pd.getString("empId"));
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
			Examine ex = examineService.getOne(pd.getString("id"));
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("idcard", ex.getEmpId());
			Employee employee = employeeService.selectOne(hashMap);
			mv.addObject("employee", employee);
			mv.setViewName("examine/examine_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", ex);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
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
				rti = examineService.edit(pd);
			} else {
				//pd.put("id", UuidUtil.get32UUID());
				rti = examineService.save(pd);
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
		int i = examineService.delete(id);
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
	    	
	    	if ((pd.getString("name") != null) && (!"".equalsIgnoreCase(pd.getString("name").trim()))) {
		    	 String name= URLDecoder.decode(pd.getString("name"), "utf-8");
		    	 pd.put("name", name);
		    	}
	    	if ((pd.getString("cyzgCard") != null) && (!"".equalsIgnoreCase(pd.getString("cyzgCard").trim()))) {
		    	 String cyzgCard= URLDecoder.decode(pd.getString("cyzgCard"), "utf-8");
		    	 pd.put("cyzgCard", cyzgCard);
		    	}
	      Map dataMap = new HashMap();
	      List titles = new ArrayList();

	      titles.add("编号");
	      titles.add("姓名");
	      titles.add("身份证号");
	      titles.add("从业资格证号");
	      titles.add("扣分项");
	      titles.add("扣分分数");
	      titles.add("日期");	
	      titles.add("备注");	      
	      dataMap.put("titles", titles);

	      List emList = this.examineService.excelList(pd);
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getIntegr("id").toString());
	        vpd.put("var2", ((PageData)emList.get(i)).getString("name"));
	        vpd.put("var3", ((PageData)emList.get(i)).getString("empId"));
	        vpd.put("var4", ((PageData)emList.get(i)).getString("cyzgCard"));
	        vpd.put("var5", ((PageData)emList.get(i)).getString("ordinal"));
	        vpd.put("var6", ((PageData)emList.get(i)).getString("scoring"));
	        vpd.put("var7", ((PageData)emList.get(i)).getString("examineTime"));
	        vpd.put("var8", ((PageData)emList.get(i)).getString("remark"));
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
