/**  
 * @Title:  EmployeeRegisterController.java   
 * @Package cn.slkj.taxi.controller.employee   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月16日 下午12:06:40   
 * @version V1.0 
 */
package cn.slkj.taxi.controller.employee;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.EmployeeCancel;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.CancelRegfileService;
import cn.slkj.taxi.service.EmployeeCancelService;
import cn.slkj.taxi.service.EmployeeService;
import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.FileUpload;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.PathUtil;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * @ClassName:  EmployeeCancelController   
 * @Description:资格证注销 
 * @author: wangling 
 * @date:   2018年12月10日 上午10:17:30   
 *
 */
@Controller
@RequestMapping({"/cancelReg"})
public class CancelRegfileController extends BaseController {
	
	@Autowired
	private EmployeeCancelService employeeCancelService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private CancelRegfileService cancelRegfileService;

	@RequestMapping({ "/listPage" })
	public ModelAndView listPage() throws Exception {
		ModelAndView mv = new ModelAndView();
		try {			
			mv.setViewName("cancel_reg/employee_cancel_list");			
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/checkListPage" })
	public ModelAndView checkListPage(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("cancel_reg/employee_cancel_checklist");
		return mv;
	}
	
	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/checkList", method = { RequestMethod.POST })
	public EPager<EmployeeCancel> checkList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		pd.put("flag", Integer.valueOf(1));
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
		List<EmployeeCancel> list = employeeCancelService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<EmployeeCancel>(pageList.getPaginator().getTotalCount(), list);
	}
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<EmployeeCancel> list(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		pd.put("flag", Integer.valueOf(1));
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
		List<EmployeeCancel> list = employeeCancelService.list(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<EmployeeCancel>(pageList.getPaginator().getTotalCount(), list);
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
			mv.setViewName("cancel_reg/cancel_reg_edit");
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
	      EmployeeCancel varList = this.employeeCancelService.selectOne(pd);
	      mv.setViewName("cancel_reg/employee_cancel_show");
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
	public boolean save(@RequestParam(value="cancelRegfile", required=false) MultipartFile cancelRegfile,
			EmployeeCancel employeeCancel,HttpSession session)  throws Exception{
		System.out.println(cancelRegfile.isEmpty());
		System.out.println(cancelRegfile.getName());
		System.out.println(cancelRegfile.getSize());
		PageData pd = new PageData();
		try {
			pd = getPageData();
			
			int rti = 0;
			User user = (User)session.getAttribute("sessionUser");	
			employeeCancel.setCompany(user.getDepartName());
			employeeCancel.setAddtime(DateUtil.getTime());
			employeeCancel.setFlag(Integer.valueOf(1));
			String id = employeeCancel.getId();
			if (Tools.notEmpty(id)) {
				rti = employeeCancelService.edit(employeeCancel);
			} else {
				//pd.put("idcard", employeeCancel.getIdcard());
				//if(){
				//pd.put("id", (DateUtil.getDayss() + new Random().nextInt()).substring(0, 15).replace("-", ""));
				
				employeeCancel.setId((DateUtil.getDayss() + new Random().nextInt()).substring(0, 15).replace("-", ""));
				rti = employeeCancelService.save(employeeCancel);
				if((!cancelRegfile.isEmpty())&&(cancelRegfile!=null)){
					 String ffile = DateUtil.getDays(); String fileName = "";
					 String filePath = PathUtil.getClasspath() + "uploadFiles/uploadImgs/" + ffile;
				     fileName = FileUpload.fileUp(cancelRegfile, filePath, get32UUID());
					pd.put("id", UuidUtil.get32UUID());
					pd.put("pid", employeeCancel.getId());
					pd.put("path", ffile + "/" + fileName);
					pd.put("createtime", DateUtil.getTime());
					cancelRegfileService.savePic(pd);
				}
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
		int i = employeeCancelService.delete(id);
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
		int i = employeeCancelService.updateStatus(pd);
		try {
			if (i > 0) {
				if(status=="2"||"2".equals(status)){
					for(int j=0;j<ids.length;j++){
						PageData erpd = new PageData();
						erpd.put("id", ids[j]);
						EmployeeCancel employeeCancel=employeeCancelService.selectOne(erpd);
						//System.out.println(employeeRegister.getCarid()+"&&&&&&&&&&&&&&&");
						 PageData mypd = new PageData();
				          mypd.put("carid", "");
				          mypd.put("cartype", "");
				          mypd.put("idcard", employeeCancel.getIdcard());
				          //mypd.put("company", employeeCancel.getCompany());
				          mypd.put("engageConn", "");
				          mypd.put("engageTime", "");
				          mypd.put("contractSrtCount", "");
				          mypd.put("contractEndCount", "");
				          mypd.put("registerDate", "");
				          mypd.put("cancelDate", employeeCancel.getPasstime());
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
	
}
