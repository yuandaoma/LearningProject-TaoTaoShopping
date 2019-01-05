package com.edu.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.edu.taotao.portal.service.IUserService;
import com.edu.taotao.portal.service.impl.UserServiceImpl;
import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;

/**
 * @author ZhuTao
 * @version 1.0
 * @Date 2017年10月10日
 * @description 用于用户的登录拦截实现
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		TbUser tbUser = userService.getUserFromSSOByToken(token);
		if (tbUser == null) {
			// 跳回到之前的页面
			response.sendRedirect(userService.ssoUrl + userService.ssoApiShowPageLogin + "?redirect=" + request.getRequestURL());
			return false;
		}
		//设置用户到requset中
		request.setAttribute("user", tbUser);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 方法执行之后，返回ModelAndView之后

	}

	@Override
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 方法执行之后，返回ModelAndView之后，响应用户之后

	}

}
