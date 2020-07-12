package cn.slkj.taxi.controller.taxicar;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Taxicar;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.service.TaxicarService;
import cn.slkj.taxi.util.DateUtil;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.FileUtil;
import cn.slkj.taxi.util.JsonResult;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.Tools;

@Controller
@RequestMapping(value = "/taxicarChange")
public class TaxicarChangeController extends BaseController {
	@Autowired
	private TaxicarService taxicarService;

	/* 跳转页面 */
	// 变更查询页面
	@RequestMapping("/taxicarChangeListPage")
	public ModelAndView taxicarChangeListPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("sessionUser");
		if (null == user.getDepartName() ) {
			mv.addObject("flag", "0");
		} else {
			mv.addObject("flag", "1");
		}
		mv.setViewName("taxi_car_change/taxi_car_change_list");
		return mv;
	}

	// 变更审核页面
	@RequestMapping("/taxicarChangeCheckListPage")
	public String taxicarChangeCheckListPage() {
		return "taxi_car_change/taxi_car_change_check";
	}

	@RequestMapping({ "/goChangeInfo" })
	public ModelAndView goChangeInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			Taxicar taxicar = taxicarService.queryOneChange(pd);
			mv.setViewName("taxi_car_change/taxi_car_change_save");
			mv.addObject("pd", taxicar);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	@RequestMapping({ "/goChangeEdit" })
	public ModelAndView goChangeEdit() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		try {
			Taxicar taxicar = taxicarService.queryOneChange(pd);
			mv.setViewName("taxi_car_change/taxi_car_change_edit");
			mv.addObject("pd", taxicar);
		} catch (Exception e) {
			this.logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 查询列表，返回easyUI数据格式
	 */
	@ResponseBody
	@RequestMapping(value = "/list", method = { RequestMethod.POST })
	public EPager<Taxicar> getAllTaxicar(HttpSession session) throws IOException {
		String sortString = "transferDate.DESC";
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		User user = (User) session.getAttribute("sessionUser");
 
		
		if ((user.getDepartName() != null) && (!user.getDepartName().trim().equals(""))) {
			pd.put("company", user.getDepartName());
		} else {
			pd.put("company", pd.getString("company"));
		}
		
		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Taxicar> list = taxicarService.getchangeAllList(pd, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Taxicar>(pageList.getPaginator().getTotalCount(), list);
	}

	@ResponseBody
	@RequestMapping(value = "/change", method = RequestMethod.POST)
	public boolean change() {
		try {
			PageData pd = new PageData();
			pd = getPageData();
			int rti = 0;
			rti = taxicarService.change(pd);
			String id = pd.getString("id");
			String sta = pd.getString("state");
			if (sta.equals("1")) {
			//	taxicarService.delete(id);
				Taxicar taxicar = taxicarService.queryOneChange(pd);
				taxicarService.copyChange(taxicar.getId()+"");
				taxicarService.deleteChange(id);
			}else if(sta.equals("2")){
				taxicarService.deleteChange(id);
			}
			return rti > 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 保存车辆信息
	 * 
	 * @param taxicar
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public boolean changeEdit(@RequestParam(value = "ownernamepic1", required = false) MultipartFile ownernamepic1, @RequestParam(value = "vehiclepic1", required = false) MultipartFile vehiclepic1,
			Taxicar taxicar) {
		try {
			if ((vehiclepic1 != null) && (!vehiclepic1.isEmpty())) {
				taxicar.setVehiclePic(FileUtil.toByteArray(vehiclepic1.getInputStream()));
			}
			if ((ownernamepic1 != null) && (!ownernamepic1.isEmpty())) {
				taxicar.setOwnerNamePic(FileUtil.toByteArray(ownernamepic1.getInputStream()));
			}
			int i = -1;
			taxicar.setaDDTIME(DateUtil.getTime());
			int rti = 0;
			// String id = pd.getString("id");
			String id = taxicar.getId();
			if (Tools.notEmpty(id)) {
				rti = taxicarService.changeEdit(taxicar);
			}  
			return rti > 0 ? true : false;
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping({ "/getVehiclepic" })
	public void getVehiclepic(HttpServletResponse response) throws IOException {
		try {
			PageData pd = new PageData();
			pd = getPageData();
			Taxicar taxicar = taxicarService.queryOneChange(pd);
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
			Taxicar taxicar = taxicarService.queryOneChange(pd);
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
	
	
	@ResponseBody
	@RequestMapping(value = "/updateListByChange")
	public JsonResult updateListByChange(@RequestParam(value = "ids[]") String[] ids) {
		int i = taxicarService.updateListByChange(ids);
		try {
			if (i > 0) {
				taxicarService.deleteChangeIds(ids);
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
	@RequestMapping(value = "/BatchRows")
	public JsonResult deletes(@RequestParam(value = "ids[]") String[] ids) {
		int i = taxicarService.BatchRows(ids);
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
}
