/**
 * 
 */
package com.ibm.upskill.microcervices.zuulapigatewayserver;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @author GangaRajuUdimudi
 *
 */
@Component
public class ZuulLoggingFilter extends ZuulFilter{
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean shouldFilter() {
		/*
		 * Write Logic to decide this filter should execute for every request or not
		 * using Business Logic
		 */
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// Here is the actual Logging Business Implementation
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		logger.info("request ==> {} request URI ==> {}", request, request.getRequestURI());
		return null;
	}

	@Override
	public String filterType() {
		// When should this filter execute. EG:- pre, post or error request
		return "pre";
	}

	@Override
	public int filterOrder() {
		// Priority orders to each filter Eg:- 1, 2, 3...
		return 1;
	}

}
