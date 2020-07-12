/**  
 * @Title:  LoginHandlerInterceptor.java   
 * @Package cn.slkj.taxi.interceptor   
 * @Description:    TODO(用一句话描述该文件做什么)   
 * @author: maxh     
 * @date:   2018年11月15日 上午8:33:57   
 * @version V1.0 
 */
package cn.slkj.taxi.interceptor;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.slkj.taxi.entity.User;
import cn.slkj.taxi.util.Const;
import cn.slkj.taxi.util.DateUtil;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description:请求拦截
 * @author: maxh
 * 
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	public String[] allowUrls;// 还没发现可以直接配置不拦截的资源，所以在代码里面来排除

	@Autowired

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		ModelAndView mav = new ModelAndView();
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);

			sb.append("-----------------------").append(DateUtil.getTime()).append("-------------------------------------\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
			sb.append("Params    : ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URI       : ").append(request.getRequestURI()).append("\n");
			System.out.println(sb.toString());
		}
		String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");
		if (null != allowUrls && allowUrls.length >= 1)
			for (String url : allowUrls) {
				if (requestUrl.contains(url)) {
					return true;
				}
			}
		// String path = request.getServletPath();
		// if (path.matches(Const.NO_INTERCEPTOR_PATH) || path.isEmpty()) {
		// return true;
		// }
		User user = (User) request.getSession().getAttribute(Const.SESSION_USER);

		if (user != null) {
			return true; // 返回true，则这个方面调用后会接着调用postHandle(), afterCompletion()
		} else {
			System.out.println("-------用户为空----------");
//			request.getRequestDispatcher("/login_toLogin").forward(request, response);
			response.sendRedirect(request.getContextPath() + "/login_toLogin");
			return false;
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
	}

	private String getParamString(Map<String, String[]> map) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e : map.entrySet()) {
			sb.append(e.getKey()).append("=");
			String[] value = e.getValue();
			if (value != null && value.length == 1) {
				sb.append(value[0]).append("\t");
			} else {
				sb.append(Arrays.toString(value)).append("\t");
			}
		}
		return sb.toString();
	}
}