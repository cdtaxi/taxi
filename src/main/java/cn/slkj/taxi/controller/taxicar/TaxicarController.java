package cn.slkj.taxi.controller.taxicar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.TaxiCarApply;
import cn.slkj.taxi.entity.Taxicar;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.ireport.JasperHelper;
import cn.slkj.taxi.service.TaxicarService;
import cn.slkj.taxi.util.Const;
import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.FileDownload;
import cn.slkj.taxi.util.FileUpload;
import cn.slkj.taxi.util.FileUtil;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.ObjectExcelRead;
import cn.slkj.taxi.util.ObjectExcelView;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.PathUtil;
import cn.slkj.taxi.util.Tools;
import cn.slkj.taxi.util.UuidUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Controller
@RequestMapping(value = "/taxicar")
public class TaxicarController extends BaseController {
	@Autowired
	private TaxicarService taxicarService;

	/* 跳转页面 */
	@RequestMapping("/taxicarListPage")
	public ModelAndView toCarListPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if (user.getDepartName() == null) {
			mv.addObject("flag", "0");
		} else {
			mv.addObject("flag", "1");
		}
//		return "taxi_car/taxi_car_list";
		mv.setViewName("taxi_car/taxi_car_list");
		return mv;
	}

	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<Taxicar> getAllTaxicar(HttpSession session) throws IOException {
		String sortString = "ADDTIME.DESC";
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		User user = (User) session.getAttribute("sessionUser");
		if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
			// pd.put("company", user.getDepartName());
			if ((user.getDepartName() != null) && (!user.getDepartName().trim().equals(""))) {
				pd.put("company", user.getDepartName());
			} else {
				pd.put("company", pd.getString("company"));
			}
		} else {
			pd.put("company", pd.getString("company"));
		}
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Taxicar> list = taxicarService.getAllList(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Taxicar>(pageList.getPaginator().getTotalCount(), list);
	}

	@RequestMapping({ "/goAdd" })
	public ModelAndView goAdd() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("taxi_car/taxi_car_edit");
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
			Taxicar taxicar = taxicarService.queryOne(pd);
			mv.setViewName("taxi_car/taxi_car_edit");
			mv.addObject("pd", taxicar);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	@RequestMapping({ "/goShow" })
	public ModelAndView goShow(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			Taxicar taxicar = taxicarService.queryOne(pd);
			mv.setViewName("taxi_car/taxi_car_show");
			mv.addObject("pd", taxicar);
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if (user.getDepartName() == null) {
				mv.addObject("flag", "0");
			} else {
				mv.addObject("flag", "1");
			}
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 查询单条信息
	 */
	@ResponseBody
	@RequestMapping(value = "/queryOne", method = { RequestMethod.POST })
	public Taxicar queryOne() {
		PageData pd = new PageData();
		pd = getPageData();
		Taxicar taxicar = taxicarService.queryOne(pd);
		return taxicar;
	}

	/**
	 * 保存车辆信息
	 * 
	 * @param taxicar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public boolean save(@RequestParam(value = "ownernamepic1", required = false) MultipartFile ownernamepic1, @RequestParam(value = "vehiclepic1", required = false) MultipartFile vehiclepic1,
			Taxicar taxicar) {
		try {
			if ((vehiclepic1 != null) && (!vehiclepic1.isEmpty())) {
				// pd.put("vehiclePic", FileUtil.toByteArray(vehiclepic1.getInputStream()));
				taxicar.setVehiclePic(FileUtil.toByteArray(vehiclepic1.getInputStream()));
			}
			if ((ownernamepic1 != null) && (!ownernamepic1.isEmpty())) {
				// pd.put("ownerNamePic", FileUtil.toByteArray(ownernamepic1.getInputStream()));
				taxicar.setOwnerNamePic(FileUtil.toByteArray(ownernamepic1.getInputStream()));
			}
			int i = -1;
			taxicar.setaDDTIME(DateUtil.getTime());
			int rti = 0;
			// String id = pd.getString("id");
			String id = taxicar.getId();
			if (Tools.notEmpty(id)) {
				rti = taxicarService.edit(taxicar);
			} else {
				taxicar.setId(UuidUtil.get32UUID());
				rti = taxicarService.save(taxicar);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/rank", method = RequestMethod.POST)
	public boolean rank(@RequestParam(value = "ownernamepic1", required = false) MultipartFile ownernamepic1, @RequestParam(value = "vehiclepic1", required = false) MultipartFile vehiclepic1,
			Taxicar taxicar) {
		try {
			if ((vehiclepic1 != null) && (!vehiclepic1.isEmpty())) {
				// pd.put("vehiclePic", FileUtil.toByteArray(vehiclepic1.getInputStream()));
				taxicar.setVehiclePic(FileUtil.toByteArray(vehiclepic1.getInputStream()));
			}
			if ((ownernamepic1 != null) && (!ownernamepic1.isEmpty())) {
				taxicar.setOwnerNamePic(FileUtil.toByteArray(ownernamepic1.getInputStream()));
			}
			int i = -1;
			taxicar.setaDDTIME(DateUtil.getTime());
			int rti = 0;
			String id = taxicar.getId();
			taxicar.setState("0");
			taxicarService.deleteChange(taxicar.getId());
			rti = taxicarService.saveChange(taxicar);
			return rti > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}

	/** 删除 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	public JsonResult delete(String id) {
		int i = taxicarService.delete(id);
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

	/** 删除 */
	@ResponseBody
	@RequestMapping(value = "/deletes")
	public JsonResult deletes(@RequestParam(value = "ids[]") String[] ids) {
		int i = taxicarService.deletes(ids);
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

	@RequestMapping({ "/getVehiclepic" })
	public void getVehiclepic(HttpServletResponse response) throws IOException {
		try {
			PageData pd = new PageData();
			pd = getPageData();
			Taxicar taxicar = taxicarService.queryOne(pd);
			byte[] data = taxicar.getVehiclePic();
			response.setContentType("image/jpg");
			OutputStream stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			System.out.println("图片无法显示");
			e.printStackTrace();
		}
	}

	@RequestMapping({ "/getOwnernamepic" })
	public void getOwnernamepic(HttpServletResponse response) throws IOException {
		try {
			PageData pd = new PageData();
			pd = getPageData();
			Taxicar taxicar = taxicarService.queryOne(pd);
			byte[] data = taxicar.getOwnerNamePic();
			response.setContentType("image/jpg");
			OutputStream stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
			stream.close();
		} catch (Exception e) {
			System.out.println("图片无法显示");
			e.printStackTrace();
		}
	}

	@RequestMapping({ "/goExcel" })
	public ModelAndView goExcel(HttpSession session) {
		ModelAndView mv = getModelAndView();
		PageData pd = new PageData();
		pd = getPageData();

		try {
			if ((pd.getString("PlateNum") != null) && (!"".equalsIgnoreCase(pd.getString("PlateNum").trim()))) {
				String PlateNum = URLDecoder.decode(pd.getString("PlateNum"), "utf-8");
				pd.put("PlateNum", PlateNum);
			}
			if ((pd.getString("OpretaCertNum") != null) && (!"".equalsIgnoreCase(pd.getString("OpretaCertNum").trim()))) {
				String OpretaCertNum = URLDecoder.decode(pd.getString("OpretaCertNum"), "utf-8");
				pd.put("OpretaCertNum", OpretaCertNum);
			}
			if ((pd.getString("OwnerName") != null) && (!"".equalsIgnoreCase(pd.getString("OwnerName").trim()))) {
				String OwnerName = URLDecoder.decode(pd.getString("OwnerName"), "utf-8");
				pd.put("OwnerName", OwnerName);
			}
			if ((pd.getString("Area") != null) && (!"".equalsIgnoreCase(pd.getString("Area").trim()))) {
				String Area = URLDecoder.decode(pd.getString("Area"), "utf-8");
				pd.put("Area", Area);
			}
			User user = (User) session.getAttribute("sessionUser");
			if ((user.getDepartName() != "超级管理员") && (!"超级管理员".equals(user.getDepartName()))) {
				if ((user.getDepartName() != null) && (!user.getDepartName().trim().equals(""))) {
					pd.put("company", user.getDepartName());
				} else {
					if ((pd.getString("company") != null) && (!"".equalsIgnoreCase(pd.getString("company").trim()))) {
						String company = URLDecoder.decode(pd.getString("company"), "utf-8");
						pd.put("company", company);
					}
				}
			} else {
				if ((pd.getString("company") != null) && (!"".equalsIgnoreCase(pd.getString("company").trim()))) {
					String company = URLDecoder.decode(pd.getString("company"), "utf-8");
					pd.put("company", company);
				}
			}

			Map dataMap = new HashMap();
			List titles = new ArrayList();

			titles.add("编号");
			titles.add("档案号");
			titles.add("营运证号");
			titles.add("车牌号");
			titles.add("公司名称");
			titles.add("车主姓名");
			titles.add("车主性别");
			titles.add("所属地区");
			titles.add("原车主");
			titles.add("原车牌号");
			titles.add("操作人员");
			titles.add("颜色");
			titles.add("车型");
			titles.add("查封记录");
			titles.add("家庭住址");
			titles.add("联系电话");
			titles.add("身份证号");
			titles.add("变更记录");
			titles.add("行驶证初次登记日期");
			titles.add("车辆审验记录");
			titles.add("吨位");
			titles.add("长");
			titles.add("宽");
			titles.add("高");
			titles.add("车架号");
			titles.add("发动机号");
			titles.add("添加日期");
			titles.add("发证日期");
			titles.add("变更日期");
			titles.add("许可证号");
			titles.add("经营性质");
			titles.add("座位数");
			titles.add("燃料类型");
			dataMap.put("titles", titles);

			List emList = this.taxicarService.excelList(pd);
			List varList = new ArrayList();
			for (int i = 0; i < emList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", ((PageData) emList.get(i)).getIntegr("ID").toString());
				vpd.put("var2", ((PageData) emList.get(i)).getString("FileNum"));
				vpd.put("var3", ((PageData) emList.get(i)).getString("OpretaCertNum"));
				vpd.put("var4", ((PageData) emList.get(i)).getString("PlateNum"));
				vpd.put("var5", ((PageData) emList.get(i)).getString("CorpName"));
				vpd.put("var6", ((PageData) emList.get(i)).getString("OwnerName"));
				// vpd.put("var7", ((PageData)emList.get(i)).getString("OwnerSex"));
				if (((PageData) emList.get(i)).getString("OwnerSex") != null) {
					if (((PageData) emList.get(i)).getString("OwnerSex").equals("0"))
						vpd.put("var7", "男");
					else if (((PageData) emList.get(i)).getString("OwnerSex").equals("1"))
						vpd.put("var7", "女");
					else
						vpd.put("var7", "不详");
				} else {
					vpd.put("var7", "不详");
				}
				vpd.put("var8", ((PageData) emList.get(i)).getString("Area"));
				/*
				 * if (((PageData)emList.get(i)).getString("Area") != null) { if
				 * (((PageData)emList.get(i)).getString("Area").equals("0")) vpd.put("var8",
				 * "市区"); else if (((PageData)emList.get(i)).getString("Area").equals("1"))
				 * vpd.put("var8", "双滦"); else if
				 * (((PageData)emList.get(i)).getString("Area").equals("2")) vpd.put("var8",
				 * "双滦代管"); else vpd.put("var8", "不详"); } else { vpd.put("var8", "不详"); }
				 */
				vpd.put("var9", ((PageData) emList.get(i)).getString("OrigOwnerName"));
				vpd.put("var10", ((PageData) emList.get(i)).getString("OrigPlateNum"));
				vpd.put("var11", ((PageData) emList.get(i)).getString("EmployPerson"));
				vpd.put("var12", ((PageData) emList.get(i)).getString("Color"));
				vpd.put("var13", ((PageData) emList.get(i)).getString("Sign"));
				vpd.put("var14", ((PageData) emList.get(i)).getString("SealRecord"));
				vpd.put("var15", ((PageData) emList.get(i)).getString("Address"));
				vpd.put("var16", ((PageData) emList.get(i)).getString("PhoneNum"));
				vpd.put("var17", ((PageData) emList.get(i)).getString("IDNumber"));
				vpd.put("var18", ((PageData) emList.get(i)).getString("OwnerChange"));
				vpd.put("var19", ((PageData) emList.get(i)).getString("DrvLicenseDate"));
				vpd.put("var20", ((PageData) emList.get(i)).getString("VehicleInspRec"));
				vpd.put("var21", ((PageData) emList.get(i)).getString("TonsSeat"));
				vpd.put("var22", ((PageData) emList.get(i)).getString("CarLen"));
				vpd.put("var23", ((PageData) emList.get(i)).getString("CarWidth"));
				vpd.put("var24", ((PageData) emList.get(i)).getString("CarHigh"));
				vpd.put("var25", ((PageData) emList.get(i)).getString("FrameNumber"));
				vpd.put("var26", ((PageData) emList.get(i)).getString("EngineNumber"));
				vpd.put("var27", ((PageData) emList.get(i)).getString("ADDTIME"));
				vpd.put("var28", ((PageData) emList.get(i)).getString("CheckDate"));
				vpd.put("var29", ((PageData) emList.get(i)).getString("TransferDate"));
				vpd.put("var30", ((PageData) emList.get(i)).getString("LicenseKey"));
				vpd.put("var31", ((PageData) emList.get(i)).getString("ManageNature"));
				vpd.put("var32", ((PageData) emList.get(i)).getString("Seating"));
				vpd.put("var33", ((PageData) emList.get(i)).getString("FuelType"));
				varList.add(vpd);
			}

			dataMap.put("varList", varList);

			ObjectExcelView erv = new ObjectExcelView();

			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			System.out.println(e.toString());
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	@RequestMapping({ "/goUploadExcel" })
	public ModelAndView goUploadExcel() {
		ModelAndView mv = new ModelAndView();
		try {
			mv.setViewName("taxi_car/taxi_car_upload_excel");
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}

	@RequestMapping({ "/downExcel" })
	public void downExcel(HttpServletResponse response) throws Exception {
		FileDownload.fileDownload(response, PathUtil.getClasspath() + "uploadFiles/templetFile/" + "taxicar.xls", "taxicar.xls");
	}

	@ResponseBody
	@RequestMapping(value = "/readExcel", method = RequestMethod.POST)
	public int readExcel(@RequestParam(value = "excel", required = false) MultipartFile excel) {
		try {
			Taxicar taxicar = new Taxicar();
			int rti = 0;
			if ((excel != null) && (!excel.isEmpty())) {
				String filePath = PathUtil.getClasspath() + "uploadFiles/file/";
				String fileName = FileUpload.fileUp(excel, filePath, "taxicarexcel");
				List listPd = ObjectExcelRead.readExcel(filePath, fileName, 1, 0, 0);
				for (int i = 0; i < listPd.size(); i++) {
//					taxicar.setId(UuidUtil.get32UUID());
					taxicar.setFileNum(((PageData) listPd.get(i)).getString("var1"));
					taxicar.setOpretaCertNum(((PageData) listPd.get(i)).getString("var2"));
					taxicar.setPlateNum(((PageData) listPd.get(i)).getString("var3"));
					taxicar.setCorpName(((PageData) listPd.get(i)).getString("var4"));
					taxicar.setOwnerName(((PageData) listPd.get(i)).getString("var5"));
					// taxicar.setOwnerSex(((PageData)listPd.get(i)).getString("var6"));
					if (((PageData) listPd.get(i)).getString("var6").equals("男"))
						taxicar.setOwnerSex("0");
					else if (((PageData) listPd.get(i)).getString("var6").equals("女"))
						taxicar.setOwnerSex("1");
					else
						taxicar.setOwnerSex("");

					taxicar.setArea(((PageData) listPd.get(i)).getString("var7"));
					/*
					 * if (((PageData)listPd.get(i)).getString("var7").equals("市区"))
					 * taxicar.setArea("0"); else if
					 * (((PageData)listPd.get(i)).getString("var7").equals("双滦"))
					 * taxicar.setArea("1"); else if
					 * (((PageData)listPd.get(i)).getString("var7").equals("双滦代管"))
					 * taxicar.setArea("2"); else taxicar.setArea("");
					 */
					taxicar.setOrigOwnerName(((PageData) listPd.get(i)).getString("var8"));
					taxicar.setOrigPlateNum(((PageData) listPd.get(i)).getString("var9"));
					taxicar.setEmployPerson(((PageData) listPd.get(i)).getString("var10"));
					taxicar.setColor(((PageData) listPd.get(i)).getString("var11"));
					taxicar.setSign(((PageData) listPd.get(i)).getString("var12"));
					taxicar.setSealRecord(((PageData) listPd.get(i)).getString("var13"));
					taxicar.setAddress(((PageData) listPd.get(i)).getString("var14"));
					taxicar.setPhoneNum(((PageData) listPd.get(i)).getString("var15"));
					taxicar.setiDNumber(((PageData) listPd.get(i)).getString("var16"));
					taxicar.setOwnerChange(((PageData) listPd.get(i)).getString("var17"));
					taxicar.setDrvLicenseDate(((PageData) listPd.get(i)).getString("var18"));
					taxicar.setVehicleInspRec(((PageData) listPd.get(i)).getString("var19"));
					taxicar.setTonsSeat(((PageData) listPd.get(i)).getString("var20"));
					taxicar.setCarLen(((PageData) listPd.get(i)).getString("var21"));
					taxicar.setCarWidth(((PageData) listPd.get(i)).getString("var22"));
					taxicar.setCarHigh(((PageData) listPd.get(i)).getString("var23"));
					taxicar.setFrameNumber(((PageData) listPd.get(i)).getString("var24"));
					taxicar.setEngineNumber(((PageData) listPd.get(i)).getString("var25"));
					taxicar.setaDDTIME(DateUtil.getTime());
					taxicar.setCheckDate(((PageData) listPd.get(i)).getString("var27"));
					taxicar.setTransferDate(((PageData) listPd.get(i)).getString("var28"));
					taxicar.setLicenseKey(((PageData) listPd.get(i)).getString("var29"));
					taxicar.setManageNature(((PageData) listPd.get(i)).getString("var30"));
					taxicar.setSeating(((PageData) listPd.get(i)).getString("var31"));
					taxicar.setFuelType(((PageData) listPd.get(i)).getString("var32"));
					HashMap<String, Object> hashMap = new HashMap<String, Object>();
					hashMap.put("PlateNum", taxicar.getPlateNum());
					if (taxicarService.queryOne(hashMap) != null)
						continue;
					taxicarService.save(taxicar);
					rti++;
				}
			}

			return rti;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return 0;
		}
	}

	@RequestMapping({ "/TradingCards" })
	public String TradingCards(Model model) {
		PageData pd = new PageData();
		pd = getPageData();
		try {
			pd.put("id", pd.get("id"));
			Taxicar taxicar = taxicarService.queryOne(pd);
			List<Taxicar> TaxiCarList = new ArrayList<>();

			String sd = DateUtil.computeDate(taxicar.getCheckDate());
			taxicar.setCheckDateFormat(DateUtil.fomatDate(sd));
			TaxiCarList.add(taxicar);
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(TaxiCarList);
			model.addAttribute("url", "/jasper/TradingCards.jrxml");
			model.addAttribute("format", "pdf");
			model.addAttribute("datasource", jrDataSource);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return "iReportView";
	}

	/** ----------------业务申请--------------------------- */
	/* 跳转页面 */
	@RequestMapping("/taxicarApplyListPage")
	public ModelAndView taxicarApplyListPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("taxi_car/taxi_car_apply_list");
		return mv;
	}

	@RequestMapping({ "/applyPage" })
	public String applyPage(Model model) {
		PageData pd = new PageData();
		pd = getPageData();
		try {
			pd.put("id", pd.get("id"));
			TaxiCarApply taxiCarApply = new TaxiCarApply();
			Taxicar taxicar = taxicarService.queryOne(pd);

			taxiCarApply.setCorpName(taxicar.getCorpName());
			taxiCarApply.setApplicant(taxicar.getOwnerName());
			String mt = pd.get("matter") + "";
			if (mt.equals("1")) {
				taxiCarApply.setMatter("车辆更新");
			} else if (mt.equals("2")) {
				taxiCarApply.setMatter( "变更业务名称");
			} else if (mt.equals("3")) {
				taxiCarApply.setMatter("丢失补办");
			}

			taxiCarApply.setNewPlateNum(taxicar.getPlateNum());
			taxiCarApply.setOldPlateNum(taxicar.getOrigPlateNum());
			taxiCarApply.setOpretaCertNum("冀"+taxicar.getOpretaCertNum());

			List<TaxiCarApply> taxiCarApplyList = new ArrayList<>();
			taxiCarApplyList.add(taxiCarApply);
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(taxiCarApplyList);
			model.addAttribute("url", "/jasper/applyPage.jrxml");
			model.addAttribute("format", "pdf");
			model.addAttribute("datasource", jrDataSource);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return "iReportView";
	}
}
