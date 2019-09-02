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
		StringBuffer buffer = new StringBuffer();
		Collection<ConfigAttribute>  def = SecurityConfig.createList("NOT_ALLOWED_1");
		
		
		FilterInvocation fi = (FilterInvocation) object;
		String url = fi.getHttpRequest().getRequestURI();
		buffer.append("url:"+url);
		buffer.append("\n");
		String path = calculatePath(url);
		if(excludeInternalMemory(path)){
			return null;
		};
		buffer.append("path:"+path);
		buffer.append("\n");
		Link l = dbUrlComponent.get(path);
		if(l != null){
			buffer.append("path:"+path+" > "+l.getPath());
			buffer.append("\n");
		};
		if(l != null){
			buffer.append("permisible:"+l.isPermitAll());
			buffer.append("\n");
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
