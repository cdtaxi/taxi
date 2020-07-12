package cn.slkj.taxi.controller.law;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.controller.base.BaseController;
import cn.slkj.taxi.service.CaseService;
import cn.slkj.taxi.util.PageData;

@Controller
@RequestMapping({ "/law" })
public class LawController extends BaseController {
	@RequestMapping({ "/lawMainPage" })
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("law/law_main");
		return mv;
	}
	@RequestMapping({ "/lawNum" })
	public ModelAndView lawNum() {
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = getPageData();
		String num =pd.getString("num");
		System.out.println("==============="+num);
		mv.setViewName("law/law_"+num);
		return mv;
	}
}
