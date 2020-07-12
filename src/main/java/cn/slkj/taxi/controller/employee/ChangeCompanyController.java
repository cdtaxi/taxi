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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.ChangeCompany;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.EmployeeContinueRegister;
import cn.slkj.taxi.entity.EmployeeReplaceSign;
import cn.slkj.taxi.entity.Role;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.ChangeCompanyService;
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


@Controller
@RequestMapping({"/changeCompany"})
public class ChangeCompanyController extends BaseController {
	
	@Autowired
	private ChangeCompanyService changeCompanyService;
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping({ "/listPage" })
	public ModelAndView listPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		try {			
			mv.setViewName("change_company/change_company_list");			
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/checkListPage" })
	public ModelAndView checkListPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("change_company/change_company_checklist");
		return mv;
	}
	
	
	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<ChangeCompany> employeeList(HttpSession session) {
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
		List<ChangeCompany> list = changeCompanyService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<ChangeCompany>(pageList.getPaginator().getTotalCount(), list);
	}
	@ResponseBody
	@RequestMapping(value = "/checkList", method = { RequestMethod.POST })
	public EPager<ChangeCompany> checkList(HttpSession session) {
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
		List<ChangeCompany> list = changeCompanyService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<ChangeCompany>(pageList.getPaginator().getTotalCount(), list);
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
			mv.setViewName("change_company/change_company_edit");
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
	    	ChangeCompany varList = this.changeCompanyService.selectOne(pd);
	      mv.setViewName("change_company/change_company_show");
	      mv.addObject("varList", varList);
	      mv.addObject("msg", "show");
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
				rti = changeCompanyService.update(pd);
			} else {
				pd.put("id", UuidUtil.get32UUID());
				User user = (User)session.getAttribute("sessionUser");				 
		        pd.put("new_company", user.getDepartName());
		        pd.put("addtime", DateUtil.getTime());
				rti = changeCompanyService.insert(pd);
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
		int i = changeCompanyService.delete(id);
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
		int i = changeCompanyService.updateStatus(pd);
		try {
			if (i > 0) {
				if(status=="2"||"2".equals(status)){
					for(int j=0;j<ids.length;j++){
						PageData erpd = new PageData();
						erpd.put("id", ids[j]);
						ChangeCompany changeCompany=changeCompanyService.selectOne(erpd);
						//System.out.println(employeeRegister.getCarid()+"&&&&&&&&&&&&&&&");
							PageData mypd = new PageData();
				          mypd.put("idcard", changeCompany.getCyzg_card());
				          mypd.put("company", changeCompany.getNew_company());
				          mypd.put("oldCompany", changeCompany.getOld_company());
				          //mypd.put("cancelDate", "");
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
	      titles.add("从业资格证号");
	      titles.add("姓名");
	      titles.add("原公司名称");
	      titles.add("现公司名称");
	      titles.add("状态");
	      titles.add("添加时间");
	      dataMap.put("titles", titles);

	      List emList = this.changeCompanyService.excelList(pd);
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getString("ID"));
	        vpd.put("var2", ((PageData)emList.get(i)).getString("CYZG_CARD"));
	        vpd.put("var3", ((PageData)emList.get(i)).getString("NAME"));
	        vpd.put("var4", ((PageData)emList.get(i)).getString("OLD_COMPANY"));
	        vpd.put("var5", ((PageData)emList.get(i)).getString("NEW_COMPANY"));
	        if (((PageData)emList.get(i)).getString("STATUS") != null) {
	          if (((PageData)emList.get(i)).getString("STATUS").equals("0"))
	            vpd.put("var6", "待审核");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("1"))
	            vpd.put("var6", "审核失败");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("2"))
	            vpd.put("var6", "审核通过");	        
	          else
	            vpd.put("var6", "未填写");
	        }
	        else {
	          vpd.put("var6", "未填写");
	        }	       
	        vpd.put("var7", ((PageData)emList.get(i)).getString("ADDTIME"));
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
