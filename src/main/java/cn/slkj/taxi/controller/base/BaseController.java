package cn.slkj.taxi.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.slkj.taxi.util.Logger;
import cn.slkj.taxi.util.PageData;
import cn.slkj.taxi.util.UuidUtil;

public class BaseController {
	protected Logger logger = Logger.getLogger(getClass());
	private static final long serialVersionUID = 6357869213649815390L;

	public PageData getPageData() {
		return new PageData(getRequest());
	}

	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}

	public String get32UUID() {
		return UuidUtil.get32UUID();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}
}
