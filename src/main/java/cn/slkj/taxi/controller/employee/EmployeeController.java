/**
 * 
 */
package cn.slkj.taxi.controller.employee;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.Taxicar;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.EmployeeService;
import cn.slkj.taxi.util.Const;
import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.FileDownload;
import cn.slkj.taxi.util.FileUpload;
import cn.slkj.taxi.util.FileUtil;
import cn.slkj.taxi.util.ObjectExcelRead;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.PathUtil;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * 
 * 从业人员管理
 * 
 * @author maxuh
 *
 */

@Controller
@RequestMapping({ "/employee" })
public class EmployeeController extends BaseController {
	@Autowired
	private EmployeeService employeeService;

	// 人员信息页面
	@RequestMapping("/employeeListPage")
	public String employeeListPage() {
		return "employee/employeeList";
	}

	// 新报名人员页面
	@RequestMapping("/employeeListPage0")
	public String employeeListPage0() {
		return "employee/employee_list0";
	}

	@RequestMapping({ "/listPage/{changeNum}" })
	public ModelAndView list(@PathVariable("changeNum") String changeNum) throws Exception {
		ModelAndView mv = new ModelAndView();
		if (changeNum.equals("9")) {
			mv.setViewName("employee/employee_list");
		} else if (changeNum.equals("0")) {
			mv.setViewName("employee/employee_list0");
		} else if (changeNum.equals("1")) {
			mv.setViewName("employee/employee_list1");
		} else if (changeNum.equals("2")) {
			mv.setViewName("employee/employee_list2");
		} else if (changeNum.equals("3")) {
			mv.setViewName("employee/employee_list3");
		}
		return mv;
	}

	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		try {
			HashMap<String, Object> hashMapCom = new HashMap<String, Object>();
			// List<Company> enterpriseInfo = companyService.queryComList(hashMapCom);
			mv.setViewName("employee/employee_edit");
			// mv.addObject("enterpriseInfo", enterpriseInfo);
			mv.addObject("msg", "save");
			mv.addObject("status", Integer.valueOf(3));
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping({ "/goEdit" })
	public ModelAndView goEdit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", request.getParameter("id"));
			Employee ep = this.employeeService.selectOne(hashMap);
			//HashMap<String, Object> hashMapCom = new HashMap<String, Object>();
			// List<Company> enterpriseInfo = companyService.queryComList(hashMapCom);
			mv.setViewName("employee/employee_edit");
			// mv.addObject("enterpriseInfo", enterpriseInfo);
			mv.addObject("msg", "edit");
			mv.addObject("pd", ep);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 详细信息页面
	 * 
	 * @return
	 */
	@RequestMapping({ "/goShow" })
	public ModelAndView goShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", request.getParameter("id"));
			Employee ep = this.employeeService.selectOne(hashMap);
			mv.addObject("msg", "show");
			mv.addObject("pd", ep);
			mv.setViewName("employee/employee_show");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/employeeList", method = { RequestMethod.POST })
	public EPager<Employee> employeeList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", pd.getString("name"));
		hashMap.put("status", pd.getString("status"));
		 User user = (User)session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
			hashMap.put("company", user.getDepartName());
			}
			else{
		    	  hashMap.put("company", pd.getString("company"));
		      }	
	      }else{
	    	  hashMap.put("company", pd.getString("company"));
	      }		
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Employee> list = employeeService.listPage(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Employee>(pageList.getPaginator().getTotalCount(), list);
	}

	@ResponseBody
	@RequestMapping({ "/delete" })
	public boolean delete() throws Exception {
		PageData pd = getPageData();
		int rti = employeeService.deleteById(pd.getString("id"));
		return rti > 0 ? true : false;
	}

	/**
	 * 考试报名页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "/signuplist" })
	public String signuplist() {
		return "signup/signup_list";
	}

	@RequestMapping({ "/goSignUpAdd" })
	public ModelAndView goSignUpAdd(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			// User user = (User) session.getAttribute("USERROL");
			// List<EnterpriseInfo> enterpriseInfo =
			// this.enterpriseInfoService.listAllEnt();
			mv.addObject("pd", pd);
			mv.setViewName("signup/signup_edit");
			// mv.addObject("enterpriseInfo", enterpriseInfo);
			mv.addObject("msg", "save");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/slistPage", method = { RequestMethod.POST })
	public EPager<Employee> slistPage(HttpSession session) {
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC";// 如果你想排序的话逗号分隔可以排序多列
		User user = (User) session.getAttribute(Const.SESSION_USER);
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
				hashMap.put("company", user.getDepartName());
				}
				else{
					hashMap.put("company", "管理员");
			      }	
	      }else{
	    	  hashMap.put("company", "超级管理员");
	      }	
		hashMap.put("name", pd.getString("name"));
		hashMap.put("status", pd.getString("status"));
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Employee> list = employeeService.slistPage(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Employee>(pageList.getPaginator().getTotalCount(), list);
	}

	@ResponseBody
	@RequestMapping({ "/save" })
	public boolean save(HttpSession session,HttpServletRequest request, @RequestParam(value = "photo", required = false) MultipartFile photo,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "personalId", required = false) String personalId, @RequestParam(value = "permitFilesId", required = false) String permitFilesId,
			@RequestParam(value = "name", required = false) String name, @RequestParam(value = "sex", required = false) String sex,
			@RequestParam(value = "borndate", required = false) String borndate, @RequestParam(value = "nationality", required = false) String nationality,
			@RequestParam(value = "idcard", required = false) String idcard, @RequestParam(value = "educated", required = false) String educated,
			@RequestParam(value = "phone", required = false) String phone, @RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "driveCard", required = false) String driveCard, @RequestParam(value = "driveStartDate", required = false) String driveStartDate,
			@RequestParam(value = "driveType", required = false) String driveType, @RequestParam(value = "cyzgCard", required = false) String cyzgCard,
			@RequestParam(value = "registerDate", required = false) String registerDate, @RequestParam(value = "cancelDate", required = false) String cancelDate,
			@RequestParam(value = "fwzljdCard", required = false) String fwzljdCard, @RequestParam(value = "fwzljdRepairDate", required = false) String fwzljdRepairDate,
			@RequestParam(value = "carid", required = false) String carid, @RequestParam(value = "cartype", required = false) String cartype,
			@RequestParam(value = "company", required = false) String company, @RequestParam(value = "oldCompany", required = false) String oldCompany,
			@RequestParam(value = "engageConn", required = false) String engageConn, @RequestParam(value = "engageTime", required = false) String engageTime,
			@RequestParam(value = "contractSrtCount", required = false) String contractSrtCount,
			@RequestParam(value = "contractEndCount", required = false) String contractEndCount, @RequestParam(value = "contractCount", required = false) String contractCount,
			@RequestParam(value = "infoPages", required = false) Integer infoPages, @RequestParam(value = "examPages", required = false) Integer examPages,
			@RequestParam(value = "sfhz", required = false) String sfhz, @RequestParam(value = "expReg", required = false) String expReg,
			@RequestParam(value = "reCyzgDate", required = false) String reCyzgDate, @RequestParam(value = "shzt", required = false) String shzt,
			@RequestParam(value = "decisionHours", required = false) String decisionHours, @RequestParam(value = "approvalHours", required = false) String approvalHours,
			@RequestParam(value = "status", required = false) String status) throws Exception {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		int rti = 0;
		pd.put("id", id);
		pd.put("personalId", personalId);
		pd.put("permitFilesId", permitFilesId);
		pd.put("name", name);
		pd.put("sex", sex);
		pd.put("borndate", borndate);
		pd.put("nationality", nationality);
		pd.put("idcard", idcard);
		pd.put("educated", educated);
		pd.put("phone", phone);
		pd.put("address", address);
		pd.put("driveCard", driveCard);
		pd.put("driveStartDate", driveStartDate);
		pd.put("driveType", driveType);
		pd.put("cyzgCard", cyzgCard);
		pd.put("registerDate", registerDate);
		pd.put("cancelDate", cancelDate);
		pd.put("fwzljdCard", fwzljdCard);
		pd.put("fwzljdRepairDate", fwzljdRepairDate);
		pd.put("carid", carid);
		pd.put("cartype", cartype);
		pd.put("company", company);
		pd.put("oldCompany", oldCompany);
		pd.put("engageConn", engageConn);
		pd.put("engageTime", engageTime);
		pd.put("contractSrtCount", contractSrtCount);
		pd.put("contractEndCount", contractEndCount);
		pd.put("contractCount", contractCount);
		pd.put("infoPages", infoPages);
		pd.put("examPages", examPages);
		pd.put("sfhz", sfhz);
		pd.put("expReg", expReg);
		pd.put("reCyzgDate", reCyzgDate);
		pd.put("shzt", shzt);
		pd.put("decisionHours", decisionHours);
		pd.put("approvalHours", approvalHours);
		pd.put("status", status);
		pd.put("addtime", DateUtil.getTime());
		User user = (User)session.getAttribute("sessionUser");				 
        pd.put("company", user.getDepartName());
		if ((photo != null) && (!photo.isEmpty())) {
			byte[] byteArryPhoto = FileUtil.toByteArray(photo.getInputStream());
			pd.put("photo", byteArryPhoto);
		}
		if (Tools.notEmpty(id)) {
			rti = employeeService.update(pd);
		}else{
			pd.put("id", UuidUtil.get32UUID());
			rti = employeeService.insertSelective(pd);
		}
		
		
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return rti > 0 ? true : false;
		// return mv;
	}

	@RequestMapping({ "/goSignUpEdit" })
	public ModelAndView goSignUpEdit() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", pd.getString("id"));
			Employee ep = this.employeeService.selectOne(hashMap);
			// List<EnterpriseInfo> enterpriseInfo =
			// this.enterpriseInfoService.listAllEnt();
			mv.setViewName("signup/signup_edit");
			// mv.addObject("enterpriseInfo", enterpriseInfo);
			mv.addObject("msg", "edit");
			mv.addObject("pd", ep);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 读取照片 @Title: getOwnernamepic @Description:
	 * TODO(这里用一句话描述这个方法的作用) @param: @param id @param: @param request @param: @param
	 * response @param: @throws IOException @return: void @throws
	 */
	@RequestMapping({ "/getPhoto" })
	public void getOwnernamepic(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("id", id);
			Employee employee = this.employeeService.selectOne(hashMap);
			byte[] data = employee.getPhoto();
			response.setContentType("image/jpg");
			OutputStream stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
	}

	@RequestMapping({ "/goExamResult" })
	public ModelAndView goExamResult() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			if ((pd.getString("idcard") != null) && (!"".equalsIgnoreCase(pd.getString("idcard").trim()))) {
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("idcard", pd.getString("idcard"));
				Employee employee = this.employeeService.selectOne(hashMap);
				mv.addObject("employee", employee);
			}
			mv.addObject("pd", pd);
			mv.setViewName("exam_results/exam_results_list");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	@RequestMapping({ "/changeStatus" })
	public void changeStatus(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = getPageData();
			if ((pd.getString("id") != null) && (!"".equals(pd.getString("id")))) {
				this.employeeService.update(pd);
				out.write("ok");
			} else {
				out.write("no");
			}
			out.close();
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
	}
	/**
	 * 
	 * @Title: list   
	 * @Description: 死亡库  
	 * @param: @param changeNum
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping({ "/listDiePage" })
	public ModelAndView listDie() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employee/employee_list_die");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/employeeDieList", method = { RequestMethod.POST })
	public EPager<Employee> employeeDieList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC,PERSONAL_ID.ASC,";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", pd.getString("name"));
		hashMap.put("status", pd.getString("status"));
		//hashMap.put("company", pd.getString("company"));
		 User user = (User)session.getAttribute("sessionUser");
			if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
				if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
				hashMap.put("company", user.getDepartName());
				}
				else{
			    	  hashMap.put("company", pd.getString("company"));
			      }	
		      }else{
		    	  hashMap.put("company", pd.getString("company"));
		      }
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Employee> list = employeeService.listDie(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Employee>(pageList.getPaginator().getTotalCount(), list);
	}
	/**
	 * 
	 * @Title: list   
	 * @Description: 超龄库  
	 * @param: @param changeNum
	 * @param: @return
	 * @param: @throws Exception      
	 * @return: ModelAndView      
	 * @throws
	 */
	@RequestMapping({ "/listOldAgePage" })
	public ModelAndView listOldAge() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employee/employee_list_age");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/employeeOldAgeList", method = { RequestMethod.POST })
	public EPager<Employee> employeeOldAgeList(HttpSession session) {
		PageData pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "ADDTIME.DESC,PERSONAL_ID.ASC,";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("name", pd.getString("name"));
		hashMap.put("status", pd.getString("status"));
		//hashMap.put("company", pd.getString("company"));
		User user = (User)session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			if((user.getDepartName()!=null)&&(!user.getDepartName().trim().equals(""))){
			hashMap.put("company", user.getDepartName());
			}
			else{
		    	  hashMap.put("company", pd.getString("company"));
		      }	
	      }else{
	    	  hashMap.put("company", pd.getString("company"));
	      }
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Employee> list = employeeService.listOldAge(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Employee>(pageList.getPaginator().getTotalCount(), list);
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
	 	    	
	    	if ((pd.getString("shzt") != null) && (!"".equalsIgnoreCase(pd.getString("shzt").trim()))) {
		    	 String shzt= URLDecoder.decode(pd.getString("shzt"), "utf-8");
		    	 pd.put("shzt", shzt);
		    	}
	    	if ((pd.getString("age") != null) && (!"".equalsIgnoreCase(pd.getString("age").trim()))) {
		    	 String age= URLDecoder.decode(pd.getString("age"), "utf-8");
		    	 pd.put("age", age);
		    	}
	    	 
	      Map dataMap = new HashMap();
	      List titles = new ArrayList();

	      titles.add("编号");
	      titles.add("档案号");
	      titles.add("许可案卷编号");
	      titles.add("姓名");
	      titles.add("性别");
	      titles.add("出生年月");
	      titles.add("国籍");
	      titles.add("身份证号");
	      titles.add("文化程度");
	      titles.add("联系电话");
	      titles.add("住址");
	      titles.add("驾驶证号");
	      titles.add("驾驶证初领日期");
	      titles.add("准驾车型");
	      titles.add("从业资格证号");
	      titles.add("注册时间");
	      titles.add("注销时间");
	      titles.add("服务质量监督卡号");
	      titles.add("服务质量监督卡补办日期");
	      titles.add("车牌号");
	      titles.add("车辆类型");
	      titles.add("公司");
	      titles.add("原公司");
	      titles.add("与车主关系");
	      titles.add("经营时间");
	      titles.add("承包期限开始");
	      titles.add("承包期限结束");
	      titles.add("初领从业证日期");
	      titles.add("决定时间");
	      titles.add("状态");
	      titles.add("内容页数");
	      titles.add("试卷页数");
	      titles.add("是否换证");
	      titles.add("注册有效期");
	      titles.add("补发从业证日期");
	      titles.add("生活状态");
	      titles.add("添加时间");
	      dataMap.put("titles", titles);

	      List emList = this.employeeService.excelList(pd);
	      List varList = new ArrayList();
	      for (int i = 0; i < emList.size(); i++) {
	        PageData vpd = new PageData();
	        vpd.put("var1", ((PageData)emList.get(i)).getString("ID"));
	        vpd.put("var2", ((PageData)emList.get(i)).getString("PERSONAL_ID"));
	        vpd.put("var3", ((PageData)emList.get(i)).getString("PERMIT_FILES_ID"));
	        vpd.put("var4", ((PageData)emList.get(i)).getString("NAME"));
	        if (((PageData)emList.get(i)).getString("SEX") != null) {
	          if (((PageData)emList.get(i)).getString("SEX").equals("0"))
	            vpd.put("var5", "男");
	          else if (((PageData)emList.get(i)).getString("SEX").equals("1"))
	            vpd.put("var5", "女");
	          else
	            vpd.put("var5", "不详");
	        }
	        else {
	          vpd.put("var5", "不详");
	        }

	        vpd.put("var6", ((PageData)emList.get(i)).getString("BORNDATE"));
	        vpd.put("var7", ((PageData)emList.get(i)).getString("NATIONALITY"));
	        vpd.put("var8", ((PageData)emList.get(i)).getString("IDCARD"));
	        vpd.put("var9", ((PageData)emList.get(i)).getString("EDUCATED"));
	        vpd.put("var10", ((PageData)emList.get(i)).getString("PHONE"));
	        vpd.put("var11", ((PageData)emList.get(i)).getString("ADDRESS"));
	        vpd.put("var12", ((PageData)emList.get(i)).getString("DRIVE_CARD"));
	        vpd.put("var13", ((PageData)emList.get(i)).getString("DRIVE_START_DATE"));
	        vpd.put("var14", ((PageData)emList.get(i)).getString("DRIVE_TYPE"));
	        vpd.put("var15", ((PageData)emList.get(i)).getString("CYZG_CARD"));
	        vpd.put("var16", ((PageData)emList.get(i)).getString("REGISTER_DATE"));
	        vpd.put("var17", ((PageData)emList.get(i)).getString("CANCEL_DATE"));
	        vpd.put("var18", ((PageData)emList.get(i)).getString("FWZLJD_CARD"));
	        vpd.put("var19", ((PageData)emList.get(i)).getString("FWZLJD_REPAIR_DATE"));
	        vpd.put("var20", ((PageData)emList.get(i)).getString("CARID"));
	        vpd.put("var21", ((PageData)emList.get(i)).getString("CARTYPE"));
	        vpd.put("var22", ((PageData)emList.get(i)).getString("COMPANY"));
	        vpd.put("var23", ((PageData)emList.get(i)).getString("OLD_COMPANY"));

	        if (((PageData)emList.get(i)).getString("ENGAGE_CONN") != null) {
	          if (((PageData)emList.get(i)).getString("ENGAGE_CONN").equals("0"))
	            vpd.put("var24", "车主");
	          else if (((PageData)emList.get(i)).getString("ENGAGE_CONN").equals("1"))
	            vpd.put("var24", "雇佣");
	          else
	            vpd.put("var24", "未填写");
	        }
	        else {
	          vpd.put("var24", "未填写");
	        }

	        if (((PageData)emList.get(i)).getString("ENGAGE_TIME") != null) {
	          if (((PageData)emList.get(i)).getString("ENGAGE_TIME").equals("0"))
	            vpd.put("var25", "白");
	          else if (((PageData)emList.get(i)).getString("ENGAGE_TIME").equals("1"))
	            vpd.put("var25", "夜");
	          else if (((PageData)emList.get(i)).getString("ENGAGE_TIME").equals("2"))
	            vpd.put("var25", "白夜");
	          else if (((PageData)emList.get(i)).getString("ENGAGE_TIME").equals("3"))
	            vpd.put("var25", "大包");
	          else
	            vpd.put("var25", "未填写");
	        }
	        else {
	          vpd.put("var25", "未填写");
	        }

	        vpd.put("var26", ((PageData)emList.get(i)).getString("CONTRACT_STRCOUNT"));
	        vpd.put("var27", ((PageData)emList.get(i)).getString("CONTRACT_ENDCOUNT"));
	        vpd.put("var28", ((PageData)emList.get(i)).getString("APPROVAL_HOURS"));
	        vpd.put("var29", ((PageData)emList.get(i)).getString("DECISION_HOURS"));

	        if (((PageData)emList.get(i)).getString("STATUS") != null) {
	          if (((PageData)emList.get(i)).getString("STATUS").equals("0"))
	            vpd.put("var30", "报名成功");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("1"))
	            vpd.put("var30", "考试通过");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("2"))
	            vpd.put("var30", "考试未通过");
	          else if (((PageData)emList.get(i)).getString("STATUS").equals("3"))
	            vpd.put("var30", "出证完成");
	          else
	            vpd.put("var30", "未填写");
	        }
	        else {
	          vpd.put("var30", "未填写");
	        }

	        vpd.put("var31", ((PageData)emList.get(i)).getString("INFO_PAGES"));
	        vpd.put("var32", ((PageData)emList.get(i)).getString("EXAM_PAGES"));
	        vpd.put("var33", ((PageData)emList.get(i)).getString("SFHZ"));
	        vpd.put("var34", ((PageData)emList.get(i)).getString("EXP_REG"));
	        vpd.put("var35", ((PageData)emList.get(i)).getString("RE_CYZG_DATE"));
	        vpd.put("var36", ((PageData)emList.get(i)).getString("SHZT"));
	        vpd.put("var37", ((PageData)emList.get(i)).getString("ADDTIME"));
	        varList.add(vpd);
	      }

	      dataMap.put("varList", varList);

	      ObjectExcelView erv = new ObjectExcelView();

	      mv = new ModelAndView(erv, dataMap);
	    } catch (Exception e) {
	      this.logger.error(e.toString(), e);
	    }
	    return mv;
	  }

	  @RequestMapping({ "/goUploadExcel" })
		public ModelAndView goUploadExcel() {
			ModelAndView mv = new ModelAndView();
			try {
				mv.setViewName("employee/employee_upload_excel");
			} catch (Exception e) {
				this.logger.error(e.toString(), e);
			}
			return mv;
		}
	  @RequestMapping({"/downExcel"})
	  public void downExcel(HttpServletResponse response)
	    throws Exception
	  {
	    FileDownload.fileDownload(response, PathUtil.getClasspath() + "uploadFiles/templetFile/" + "employee.xls", "employee.xls");
	  }
	  
	    @ResponseBody
		@RequestMapping(value = "/readExcel", method = RequestMethod.POST)
		public int readExcel(@RequestParam(value="excel", required=false) MultipartFile excel) {
			try {
				Employee employee=new Employee();
				 int rti = 0;
			    if ((excel != null) && (!excel.isEmpty())) {
			      String filePath = PathUtil.getClasspath() + "uploadFiles/file/";
			      String fileName = FileUpload.fileUp(excel, filePath, "employeeexcel");
			      List listPd = ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0);
			      PageData pd = new PageData();
			      for (int i = 0; i < listPd.size(); i++) {			    	 
			    	pd = getPageData();
			  		pd.put("id", UuidUtil.get32UUID());
			  		pd.put("name", ((PageData)listPd.get(i)).getString("var1"));
			  		//pd.put("sex", ((PageData)listPd.get(i)).getString("var2"));
			  		if (((PageData)listPd.get(i)).getString("var2").equals("男"))
			  			pd.put("sex","0");
			          else if (((PageData)listPd.get(i)).getString("var2").equals("女"))
			        	  pd.put("sex","1");
			          else
			        	  pd.put("sex","");
			  		pd.put("borndate", ((PageData)listPd.get(i)).getString("var3"));
			  		pd.put("nationality", ((PageData)listPd.get(i)).getString("var4"));
			  		pd.put("idcard", ((PageData)listPd.get(i)).getString("var5"));
			  		pd.put("educated", ((PageData)listPd.get(i)).getString("var6"));
			  		pd.put("phone", ((PageData)listPd.get(i)).getString("var7"));
			  		pd.put("address", ((PageData)listPd.get(i)).getString("var8"));
			  		pd.put("driveCard", ((PageData)listPd.get(i)).getString("var9"));
			  		pd.put("driveStartDate", ((PageData)listPd.get(i)).getString("var10"));
			  		pd.put("driveType", ((PageData)listPd.get(i)).getString("var11"));
			  		pd.put("company", ((PageData)listPd.get(i)).getString("var12"));
			  		pd.put("oldCompany", ((PageData)listPd.get(i)).getString("var13"));
			  		pd.put("infoPages", ((PageData)listPd.get(i)).getString("var14"));
			  		pd.put("status", "0");
			  		pd.put("addtime", DateUtil.getTime());
			    	 
			    	 HashMap<String, Object> hashMap = new HashMap<String, Object>();
					 hashMap.put("idcard", pd.getString("idcard"));
					 if(employeeService.selectOne(hashMap)!=null)
						 continue;
					 employeeService.insertSelective(pd);
			    	  rti++;
			      }
			    }				
				
				return rti;
			} catch (Exception e) {System.out.println(e.toString());
				e.printStackTrace();
				return 0;
			}
		}
}
