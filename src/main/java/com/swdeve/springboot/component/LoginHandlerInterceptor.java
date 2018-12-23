package com.swdeve.springboot.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * 
 * @author Administrator
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Object user = request.getSession().getAttribute("loginUser");
		if (user == null) {
			request.setAttribute("msg", "没有权限，请先登录");
			// 若没有登录，转发到“/index.html”请求，
			/**
			 * 转发与重定向的区别，转发是服务端完成的，在浏览器端看不到访问地址的变化； 重定向是客户端完成的，可以看到访问地址的变化
			 */
			request.getRequestDispatcher("/index.html").forward(request, response);
			return false;
		} else {
			//已登录
			return true;
		}
		
	}

}
