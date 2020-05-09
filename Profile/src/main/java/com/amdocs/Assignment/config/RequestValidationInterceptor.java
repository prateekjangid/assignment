package com.amdocs.Assignment.config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RequestValidationInterceptor extends HandlerInterceptorAdapter {

		@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		try {
			System.out.println(request.getRequestURL());
			/*int port = request.getServerPort();
			System.out.println(request.getServerPort());
			StringBuffer url = request.getRequestURL();
			String uri = request.getRequestURI();
			String host = url.substring(0, url.indexOf(uri));
			
			if(port!=8081)
			{
				response.getWriter().write("Invalid Request");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
				return false;
			}*/
			//redirect to login url.or will give forbidden msg.
			return true;
		} catch (Exception e) {
			log.error("Exception in creation connection " + e.getMessage(), e);
			response.getWriter().write(e.getMessage());
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return false;
		}
	}

}
