/**  
 * @Title:  EmployeeRegisterController.java   
 * @Package cn.slkj.taxi.controller.employee   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月16日 下午12:06:40   
 * @version V1.0 
 */
package cn.slkj.taxi.controller.employee;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.EmployeeCancel;
import cn.slkj.taxi.entity.EmployeeRepairSign;
import cn.slkj.taxi.entity.EmployeeReplaceSign;
import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.EmployeeRepairSignService;
import cn.slkj.taxi.service.EmployeeService;
import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @ClassName: EmployeeRegisterController
 * @Description: 从业资格注册
 * @author: maxh
 * @date: 2018年11月16日 下午12:06:40
 * 
 */
@Controller
@RequestMapping({"/employeeRepairSign"})
public class EmployeeRepairSignController extends BaseController {
	
	@Autowired
	private EmployeeRepairSignService employeeRepairSignService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping({ "/listPage" })
	public ModelAndView listPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		try {			
			mv.setViewName("employee_repair_sign/employee_repair_sign_list");			
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/fircheckListPage" })
	public ModelAndView fircheckListPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employee_repair_sign/employee_repair_sign_firchecklist");
		return mv;
	}
	@RequestMapping({ "/seccheckListPage" })
	public ModelAndView seccheckListPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employee_repair_sign/employee_repair_sign_secchecklist");
		return mv;
	}
	
	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<EmployeeRepairSign> list(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		User user = (User)session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
				pd.put("company", user.getDepartName());
				}
				else{
					pd.put("company", "管理员");
			      }	
	      }else{
	    	  pd.put("company", "超级管理员");
	      }	
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<EmployeeRepairSign> list = employeeRepairSignService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<EmployeeRepairSign>(pageList.getPaginator().getTotalCount(), list);
	}
	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/firstCheckList", method = { RequestMethod.POST })
	public EPager<EmployeeRepairSign> firstCheckList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		User user = (User)session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
				pd.put("company", user.getDepartName());
			}
			else{
				pd.put("company", pd.getString("company"));
		      }	
	      }else{
	    	  pd.put("company", pd.getString("company"));
	      }	
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<EmployeeRepairSign> list = employeeRepairSignService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<EmployeeRepairSign>(pageList.getPaginator().getTotalCount(), list);
	}
	@ResponseBody
	@RequestMapping(value = "/secondCheckList", method = { RequestMethod.POST })
	public EPager<EmployeeRepairSign> secondCheckList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		User user = (User)session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
				pd.put("company", user.getDepartName());
			}
			else{
				pd.put("company", pd.getString("company"));
		      }	
	      }else{
	    	  pd.put("company", pd.getString("company"));
	      }	
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<EmployeeRepairSign> list = employeeRepairSignService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<EmployeeRepairSign>(pageList.getPaginator().getTotalCount(), list);
	}
	
	@RequestMapping("/goAdd")
	public ModelAndView examineAdd() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			if ((pd.getString("idcard") != null) && (!"".equalsIgnoreCase(pd.getString("idcard").trim()))) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("idcard", pd.getString("idcard"));
				hashMap.put("status", "3");
				Employee employee = this.employeeService.selectOne(hashMap);
				mv.addObject("employee", employee);
				
			}
			mv.addObject("msg", "save");
			mv.setViewName("employee_repair_sign/employee_repair_sign_edit");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	 @RequestMapping({"/goShow"})
	  public ModelAndView goShow()
	  {
	    ModelAndView mv = new ModelAndView();
	    PageData pd = new PageData();
	    pd = getPageData();
	    try
	    {
	    	EmployeeRepairSign varList = this.employeeRepairSignService.selectOne(pd);
	      mv.setViewName("employee_repair_sign/employee_repair_sign_show");
	      mv.addObject("varList", varList);
	      mv.addObject("msg", "show");
	      mv.addObject("pd", pd);
	    } catch (Exception e) {
	      this.logger.error(e.toString(), e);
	    }
	    return mv;
	  }
	@ResponseBody
	@RequestMapping(value = "/save", method = { RequestMethod.POST })
	public boolean save(HttpSession session)  throws Exception{
		
		PageData pd = new PageData();
		try {
			pd = getPageData();
			int rti = 0;
			String id = pd.getString("id");
			if (Tools.notEmpty(id)) {
				rti = employeeRepairSignService.update(pd);
			} else {
				//pd.put("id", UuidUtil.get32UUID());
				pd.put("id", (DateUtil.getDayss() + new Random().nextInt()).substring(0, 15).replace("-", ""));
				User user = (User)session.getAttribute("sessionUser");				 
		        pd.put("company", user.getDepartName());
		        pd.put("addtime", DateUtil.getTime());
				rti = employeeRepairSignService.insert(pd);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
			return false;
		}
	}
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResult deletes(String id) {
		int i = employeeRepairSignService.delete(id);
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
	@ResponseBody
	@RequestMapping(value = "/changeStatus")
	public JsonResult changeStatus(@RequestParam(value = "ids[]")String[] ids,String status)  throws Exception {
		PageData pd = new PageData();
		pd = getPageData();
		pd.put("ids", ids);
		pd.put("status", status);
		pd.put("passtime", DateUtil.getTime());
		int i = employeeRepairSignService.updateStatus(pd);
		try {
			if (i > 0) {
				if(status=="3"||"3".equals(status)){
					for(int j=0;j<ids.length;j++){
						PageData erpd = new PageData();
						erpd.put("id", ids[j]);
						EmployeeRepairSign employeeRepairSign=employeeRepairSignService.selectOne(erpd);
						//System.out.println(employeeRegister.getCarid()+"&&&&&&&&&&&&&&&");
						 PageData mypd = new PageData();
				          mypd.put("idcard", employeeRepairSign.getIdcard());
				          mypd.put("reCyzgDate", employeeRepairSign.getPasstime());
				          this.employeeService.updateByIDCard(mypd);
					}
					}
				return new JsonResult(true, "");
			} else {
				return new JsonResult(false, "操作失败！");
			}
		} catch (Exception e) {System.out.println(e.toString());
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
	    	if ((pd.getString("status") != null) && (!"".equalsIgnoreCase(pd.getString("status").trim()))) {
	    	 String status= URLDecoder.decode(pd.getString("status"), "utf-8");
	    	 pd.put("status", status);
	    	}
	    	if ((pd.getString("cyzgCard") != null) && (!"".equalsIgnoreCase(pd.getString("cyzgCard").trim()))) {
	    	 String cyzgCard= URLDecoder.decode(pd.getString("cyzgCard"), "utf-8");
	    	 pd.put("cyzgCard", cyzgCard);
	    	}
	    	if((pd.getString("right") != null) && (!"".equalsIgnoreCase(pd.getString("right").trim()))){
				User user = (User)session.getAttribute("sessionUser");
				if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
					if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
						pd.put("company", user.getDepartName());
						}
						else{
							pd.put("company", "管理员");
					      }	
			      }else{
			    	  pd.put("company", "超级管理员");
			      }
						
				}else{
					User user = (User)session.getAttribute("sessionUser");
					if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
						if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
							pd.put("company", user.getDepartName());
						}
						else{
							if ((pd.getString("company") != null) && (!"".equalsIgnoreCase(pd.getString("company").trim()))) {
					 	    	 String company= URLDecoder.decode(pd.getString("company"), "utf-8");
					 	    	 pd.put("company", company);
					 	    	}
					      }	
				      }else{
				    	  if ((pd.getString("company") != null) && (!"".equalsIgnoreCase(pd.getString("company").trim()))) {
					 	    	 String company= URLDecoder.decode(pd.getString("company"), "utf-8");
					 	    	 pd.put("company", company);
					 	    	}
				      }			
				}
	    	
	    	
	    	 
	      Map dataMap = new HashMap();
	      List titles = new ArrayList();

	      titles.add("编号");
	      titles.add("姓名");
	      titles.add("性别");
	      titles.add("出生年月");
	      titles.add("身份证号");
	      titles.add("联系电话");
	      titles.add("住址");
	      titles.add("驾驶证类型");
	      titles.add("驾驶证初领日期");
	      titles.add("原从业资格证号");
	      titles.add("初次注册日期");
	      titles.add("车牌号");
	      titles.add("公司");
	      titles.add("通过时间");
	      titles.add("状态");
	      titles.add("添加时间");
	      dataMap.put("titles", titles);

	      List emList = this.employeeRepairSignService.excelList(pd);
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getString("ID"));
	        vpd.put("var2", ((PageData)emList.get(i)).getString("NAME"));
	        if (((PageData)emList.get(i)).getString("SEX") != null) {
	          if (((PageData)emList.get(i)).getString("SEX").equals("0"))
	            vpd.put("var3", "男");
	          else if (((PageData)emList.get(i)).getString("SEX").equals("1"))
	            vpd.put("var3", "女");
	          else
	            vpd.put("var3", "不详");
	        }
	        else {
	          vpd.put("var3", "不详");
	        }

	        vpd.put("var4", ((PageData)emList.get(i)).getString("BORNDATE"));
	        vpd.put("var5", ((PageData)emList.get(i)).getString("IDCARD"));
	        vpd.put("var6", ((PageData)emList.get(i)).getString("PHONE"));
	        vpd.put("var7", ((PageData)emList.get(i)).getString("ADDRESS"));
	        vpd.put("var8", ((PageData)emList.get(i)).getString("DRIVE_TYPE"));
	        vpd.put("var9", ((PageData)emList.get(i)).getString("DRIVE_START_DATE"));
	        vpd.put("var10", ((PageData)emList.get(i)).getString("OLD_SIGN_ID"));
	        vpd.put("var11", ((PageData)emList.get(i)).getString("FIRST_SIGN_DATE"));
	        vpd.put("var12", ((PageData)emList.get(i)).getString("CARID"));
	        vpd.put("var13", ((PageData)emList.get(i)).getString("COMPANY"));
	        vpd.put("var14", ((PageData)emList.get(i)).getString("PASSTIME"));

	        if (((PageData)emList.get(i)).getString("STATUS") != null) {
	          if (((PageData)emList.get(i)).getString("STATUS").equals("0"))
	            vpd.put("var15", "待审核");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("1"))
	            vpd.put("var15", "待上级审批");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("2"))
	            vpd.put("var15", "审批未通过");	  
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("3"))
		            vpd.put("var15", "审批通过");	
	          else
	            vpd.put("var15", "未填写");
	        }
	        else {
	          vpd.put("var15", "未填写");
	        }
	       
	        vpd.put("var16", ((PageData)emList.get(i)).getString("ADDTIME"));
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
