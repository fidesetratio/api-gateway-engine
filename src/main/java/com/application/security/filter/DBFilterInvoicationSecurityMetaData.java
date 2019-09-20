package com.application.security.filter;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.application.locator.component.CacheComponent;
import com.application.utils.Utility;

@Component
public class DBFilterInvoicationSecurityMetaData implements FilterInvocationSecurityMetadataSource{

	private static Logger logger = LoggerFactory.getLogger(DBFilterInvoicationSecurityMetaData.class);
	
	 @Autowired
	 private CacheComponent dbUrlComponent;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getRequestUrl();
		StringBuffer buffer = new StringBuffer();
		buffer.append("url:"+url);
		buffer.append("\n");
		
		Collection<ConfigAttribute>  def = SecurityConfig.createList("NOTALLOWED");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
				if(auth.getAuthorities().size() == 1){
					for(GrantedAuthority a:auth.getAuthorities()){
						if(a.getAuthority().trim().toUpperCase().equals("ALLOWED")){
							def = null;
							buffer.append("status url:allowed");
							logger.info(buffer.toString());
							return def;
						}
					}
					
				}
		};
		buffer.append("status url:not allowed");
		logger.info(buffer.toString());
		return def;
		
	}
	private boolean excludeInternalMemory(String url){
		if(url.trim().startsWith("/gwadmin")){
			return true;
		}
		return false;
	}
	
	private String calculatePath(String url) {
		return Utility.calculatePath(url);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
