package com.commons.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		try {
			return deal(request, response, url);
		} catch (Exception e) {
			response.sendRedirect("error");
			return false;
		}
		
	}

	private boolean deal(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		String userName = request.getParameter("userName");
		return "userName147852369".equals(userName);
	}
	
}
