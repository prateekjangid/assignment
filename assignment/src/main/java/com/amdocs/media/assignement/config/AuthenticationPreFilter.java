package com.amdocs.media.assignement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import com.amdocs.media.assignement.service.AuthenticationService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class AuthenticationPreFilter extends ZuulFilter {
	
	@Autowired
	private AuthenticationService authService;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		String username=RequestContext.getCurrentContext().getRequest().getHeader("Authorization");
		UserDetails userDetails=this.authService.loadUserByUsername(username);
		if(userDetails==null) {
			return false;
		}
		return null;
	}

	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
