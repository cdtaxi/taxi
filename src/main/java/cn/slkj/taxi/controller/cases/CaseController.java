package cn.slkj.taxi.controller.cases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.entity.Employee;
import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.entity.cases.Anjian;
import cn.slkj.taxi.entity.cases.Anyou;
import cn.slkj.taxi.entity.cases.Zfry;
import cn.slkj.taxi.service.CaseService;
import cn.slkj.taxi.util.EPager;
import cn.slkj.taxi.util.PageData;

/**
 * 
 * @ClassName: CaseController
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: maxuh
 * @date: 2020年7月4日 下午2:55:37
 *
 */
@Controller
@RequestMapping({ "/case" })
public class CaseController extends BaseController {
	@Autowired
	private CaseService caseService;

	/*
	 * 案件查询页面
	 */
	@RequestMapping({ "/listPage" })
	public ModelAndView listPage() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("case/caseList");
		return mv;
	}

	// 案件数据
	@ResponseBody
	@RequestMapping({ "/listData" })
	public EPager<Anjian> caseListPage() throws IOException {
		PageData pd = new PageData();
		pd = getPageData();

		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		 hashMap.put("grxm", pd.getString("grxm"));
		 hashMap.put("anhao", pd.getString("anhao"));
		 hashMap.put("ay", pd.getString("ay"));

		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Anjian> list = caseService.caseListPage(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Anjian>(pageList.getPaginator().getTotalCount(), list);
	}

	// 巡游无证案件添加页面
	@RequestMapping({ "/xyAddView" })
	public ModelAndView xyAddView() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		PageData jbxx = caseService.listJbxx(pd);
		mv.addObject("jbxx", jbxx);
		mv.setViewName("case/case_add_xywz");

		return mv;
	}

	// ---------------信息管理---------------
	// 执法人员页面
	@RequestMapping({ "/zfryList" })
	public ModelAndView zfryList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("case/zfryList");
		return mv;
	}

	// 执法人员数据
	@ResponseBody
	@RequestMapping({ "/zfryListData" })
	public EPager<Zfry> zfryListData() throws IOException {
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// hashMap.put("name", pd.getString("name"));
		// hashMap.put("status", pd.getString("status"));

		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Zfry> list = caseService.zfryList(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Zfry>(pageList.getPaginator().getTotalCount(), list);
	}

	// 基本信息页面
	@RequestMapping({ "/basicInfo" })
	public ModelAndView basicInfo() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		PageData jbxx = caseService.listJbxx(pd);
		mv.addObject("jbxx", jbxx);
		mv.setViewName("case/basicInfo");
		return mv;
	}

	// 案由
	@RequestMapping({ "/anyouList" })
	public ModelAndView anyouList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("case/anyouList");
		return mv;
	}
	@ResponseBody
	@RequestMapping({ "/anyouListData" })
	public EPager<Anyou> anyouListData() throws IOException {
		PageData pd = new PageData();
		pd = getPageData();
		Integer rows = pd.getIntegr("rows");
		Integer page = pd.getIntegr("page");
		String sortString = "";// 如果你想排序的话逗号分隔可以排序多列
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		// hashMap.put("name", pd.getString("name"));
		// hashMap.put("status", pd.getString("status"));

		PageBounds pageBounds = new PageBounds(page, rows, Order.formString(sortString));
		List<Anyou> list = caseService.anyouList(hashMap, pageBounds);
		PageList pageList = (PageList) list;
		return new EPager<Anyou>(pageList.getPaginator().getTotalCount(), list);
	}
}
