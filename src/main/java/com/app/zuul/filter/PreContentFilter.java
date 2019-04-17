package com.app.zuul.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


/**
 * 
 * @author Patar Timotius
 * 
 * 
 * We don't need these filter, yet in the future we can implement new thing
 * on top of these filter.
 *
 */

public class PreContentFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    
		Enumeration<String> headerNames = request.getHeaderNames();
	    while(headerNames.hasMoreElements()) {
	    	String headerName = headerNames.nextElement();
	    	Enumeration<String> headers = request.getHeaders(headerName);
	    	while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				System.out.println(headerName+":"+headerValue);
	    	}
	    }
	    
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
