package com.application.security.filter;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.app.manager.model.Link;
import com.application.locator.component.DBUrlComponent;
import com.application.utils.Utility;

@Component
public class DBFilterInvoicationSecurityMetaData implements FilterInvocationSecurityMetadataSource{

	private static Logger logger = LoggerFactory.getLogger(DBFilterInvoicationSecurityMetaData.class);
	
	 @Autowired
	 private DBUrlComponent dbUrlComponent;
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
		Collection<ConfigAttribute>  def = SecurityConfig.createList("NOT_ALLOWED_1");
		
		
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getHttpRequest().getRequestURI();
		logger.info("url : "+url);
		
		
		String path = calculatePath(url);
		
		if(excludeInternalMemory(path)){
			return null;
		};
		

			
		logger.info("all dbComponent:"+dbUrlComponent);
		logger.info("path:"+path);
		Link l = dbUrlComponent.get(path);
		
		dbUrlComponent.showListLink();
		
		
		if(l != null){
			logger.info("is permitable:"+l.isPermitAll());
			if(l.isPermitAll()){
			   def = null;	
			}else{
				List<String> roles = l.getRoles();
				if(roles.size()>0){
					String[] rolesA = new String[roles.size()];
					rolesA = roles.toArray(rolesA);
					def = SecurityConfig.createList(rolesA);
				 };
				
			};
		}
	
		
		return def;
	}

	
	private boolean excludeInternalMemory(String url){
	
		if(url.trim().startsWith("/gwadmin")){
			return true;
		}
		return false;
	}
	
	private String calculatePath(String url) {
		// TODO Auto-generated method stub
		return Utility.calculatePath(url);
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
