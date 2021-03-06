package com.application.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;
import com.application.locator.component.CacheComponent;
import com.application.security.SinarmasMsigTokenRemoteServices;
import com.application.security.authentication.NotPermitAllAuthentication;
import com.application.security.authentication.PermitAllAuthentication;
import com.application.utils.Utility;
/**
 * 
 * @author Patar Timotius
 * 
 * Since no plugin for these authentication, we need to create one.
 * it is extended from OncePerRequestFilter
 *
 */
public class CustomAuthenticationFilter extends OncePerRequestFilter{
	
	 private Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    
	 
	 private CacheComponent dbUrlComponent;
	 
	 private Environment env;
	 
	
	private  SinarmasMsigTokenRemoteServices remoteTokenServices;
	 public CustomAuthenticationFilter(SinarmasMsigTokenRemoteServices remoteTokenServices,CacheComponent dbUrlComponent,Environment env) {
		// TODO Auto-generated constructor stub
		 this.remoteTokenServices = remoteTokenServices;
		 this.dbUrlComponent = dbUrlComponent;
		 this.env = env;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		StringBuffer buffer = new StringBuffer();
		buffer.append("requestIp:"+request.getRequestURI());
		buffer.append("\n");
		Authentication authentication = new NotPermitAllAuthentication();		
		
		
		if(excludeInternalMemory(request.getRequestURI().trim())) {
			authentication = new PermitAllAuthentication();
		}
		
		
		String pathToCheck = Utility.calculatePath(request.getRequestURI());
		Link l = dbUrlComponent.get(pathToCheck);
		buffer.append("path:"+pathToCheck);
		buffer.append("\n");
		if(l != null){	
			buffer.append("destination url:"+l.getUrl());
			if(l.getUrl() != null){
					if(l.isActive()){
						 buffer.append("\n");
						 buffer.append("Active:Y");
								 if(l.isPermitAll()){
									 buffer.append("\n");
									 buffer.append("permit all:Y");
									 authentication = new PermitAllAuthentication();
								 }else{
									 buffer.append("\n");
									 buffer.append("permit all:N");
									 authentication = new NotPermitAllAuthentication();
								 }
							
					}else{
						 authentication = new NotPermitAllAuthentication();
						 buffer.append("\n");
						 buffer.append("permit all:N");
					}
				
			}
			
		}else{
			buffer.append("destination url: none ");
		}
		
		
		String headerAuthorization = request.getHeader("Authorization");
		if(headerAuthorization != null && l.isActive())
		{
			buffer.append("authorization = yes");
			buffer.append("\n");
			if(headerAuthorization.contains("Bearer")) {
					buffer.append("bearer = yes");
					headerAuthorization = headerAuthorization.replaceAll("Bearer","");
					headerAuthorization = headerAuthorization.trim();
					buffer.append("bearer value ="+headerAuthorization);
					buffer.append("\n");
					buffer.append("validationUrl:"+pathToCheck);
					buffer.append("\n");
						if(l != null){
							buffer.append("service is exist value:"+l.getServiceId());
							buffer.append("\n");
							Long providerId = l.getProviderId();
							AuthenticationProvider provider = dbUrlComponent.getProviderById(providerId);
							if(provider != null){
								buffer.append("provider is exist="+providerId);
								buffer.append("\n");
								buffer.append("provider url ="+provider.getUrl());
								buffer.append("\n");
								buffer.append("clientId ="+provider.getClientId());
								buffer.append("\n");
								buffer.append("secretId ="+provider.getClientSecret());
								buffer.append("\n");
								remoteTokenServices.setClientId(provider.getClientId().trim());
								remoteTokenServices.setClientSecret(provider.getClientSecret().trim());
								remoteTokenServices.setUrl(provider.getUrl().trim());
							};
							
						};
						
			try {
				
				OAuth2Authentication oauth2Authentication = remoteTokenServices.loadAuthentication(headerAuthorization);
				
				boolean allowedToCheck = true;
				if(oauth2Authentication != null) {
					authentication = new PermitAllAuthentication();
					if(oauth2Authentication.getDetails() != null){
												Map<String,Object> tambahanDetail = (Map<String,Object>)oauth2Authentication.getDetails();
												if(tambahanDetail.get("aud")!=null){
													if(tambahanDetail.get("aud") instanceof ArrayList){
														ArrayList<String> arr = (ArrayList<String>)tambahanDetail.get("aud");
														if(l.isStrict()){
															String resourceId = l.getResourceid();
															if(!arr.contains(resourceId)){
																allowedToCheck = false;
																authentication = new NotPermitAllAuthentication();
															}
														};
													}
												}
												if(tambahanDetail.get("authorities") != null){
													if(tambahanDetail.get("authorities") instanceof ArrayList){
														ArrayList<String> arr = (ArrayList<String>)tambahanDetail.get("authorities");
														List<String> roles = l.getRoles();
														if(!roles.isEmpty()){
															String searchedValue[] = roles.toArray(new String[roles.size()]);
															String valuesRoles[] = arr.toArray(new String[arr.size()]);
															if(!Utility.searchInArray(searchedValue, valuesRoles)){
																authentication = new NotPermitAllAuthentication();
															};
														}
													}
												}
					
					}
					
					
					buffer.append("Authentication is exist="+oauth2Authentication.getOAuth2Request());
					buffer.append("\n");
					buffer.append("Authentication Detail is="+oauth2Authentication.getDetails());
					buffer.append("\n");
					
				}else {
					 authentication = new NotPermitAllAuthentication();
				}
					
					
				
					
					
				buffer.append("allowed to check? ="+allowedToCheck);
				buffer.append("\n");
				logger.info(buffer.toString());
			}catch(Exception e) {
				logger.error("error connection :"+e.getMessage());
				SecurityContextHolder.clearContext();
			}
			};
		}
			
		
		
		if(!(authentication instanceof NotPermitAllAuthentication)){
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	
		
		chain.doFilter(request, response);
		
	}
	
	private boolean excludeInternalMemory(String url){
		if(url.trim().startsWith("/gwadmin")){
			return true;
		}
		return false;
	}

}
