package com.application.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;
import com.application.locator.component.DBUrlComponent;
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
    
	 
	 private DBUrlComponent dbUrlComponent;
	 
	  private Environment env;
	 
	
	private  RemoteTokenServices remoteTokenServices;
	 public CustomAuthenticationFilter(RemoteTokenServices remoteTokenServices,DBUrlComponent dbUrlComponent,Environment env) {
		// TODO Auto-generated constructor stub
		 this.remoteTokenServices = remoteTokenServices;
		 this.dbUrlComponent = dbUrlComponent;
		 this.env = env;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String headerAuthorization = request.getHeader("Authorization");
		logger.info("Authorization:"+headerAuthorization);
		if(headerAuthorization != null )
		{
			
			
			if(headerAuthorization.contains("Bearer")) {
					headerAuthorization = headerAuthorization.replaceAll("Bearer","");
					headerAuthorization = headerAuthorization.trim();
					logger.info("header:"+headerAuthorization);
					logger.info("url : "+request.getRequestURI());
	
					String pathToCheck = Utility.calculatePath(request.getRequestURI());
					logger.info("pathtocheck"+pathToCheck);
					//dbUrlComponent.showListLink();
					
						Link l = dbUrlComponent.get(pathToCheck);
						if(l != null){
							logger.info("link exist with name:"+l.getServiceId());
							Long providerId = l.getProviderId();
							AuthenticationProvider provider = dbUrlComponent.getProviderById(providerId);
							if(provider != null){
								logger.info("providerId:"+providerId);
								logger.info("url:"+provider.getUrl());
								logger.info("clientId:"+provider.getClientId());
								logger.info("secretId:"+provider.getClientSecret());
								remoteTokenServices.setCheckTokenEndpointUrl(provider.getUrl());
								remoteTokenServices.setClientId(provider.getClientId());
								remoteTokenServices.setClientId(provider.getClientSecret());
								
							}else{
								remoteTokenServices.setClientId(env.getProperty("gateway.locator.prop.remote.token.clientid"));
								remoteTokenServices.setClientSecret(env.getProperty("gateway.locator.prop.remote.token.clientsecret"));
								remoteTokenServices.setCheckTokenEndpointUrl(env.getProperty("gateway.locator.prop.remote.token.services"));
							
							}
							
							
						};
						
			try {
	
				OAuth2Authentication oauth2Authentication = remoteTokenServices.loadAuthentication(headerAuthorization);
				if(oauth2Authentication != null) {
					
					System.out.println(oauth2Authentication.getOAuth2Request());
					
					SecurityContextHolder.getContext().setAuthentication(oauth2Authentication);
				}
			}catch(Exception e) {
				logger.error("error connection:"+e.getMessage());
		//		e.printStackTrace();
				SecurityContextHolder.clearContext();
			}
			};
		}
		
		/*
		 * Authentication authentication = tokenExtractor.extract(request);
		 * if(authentication != null) {
		 * 
		 * }
		 */	
		/*
		 * System.out.println("heihie"); String header =
		 * request.getHeader("Authorization");
		 * 
		 * System.out.println("authorization:"+header);
		 * 
		 * if(header != null) { List<SimpleGrantedAuthority> listAuthorities = new
		 * ArrayList<SimpleGrantedAuthority>(); listAuthorities.add(new
		 * SimpleGrantedAuthority("ROLE_USER"));
		 * 
		 * 
		 * 
		 * UsernamePasswordAuthenticationToken authResult = new
		 * UsernamePasswordAuthenticationToken("patar", "timotius",listAuthorities);
		 * SecurityContextHolder.getContext().setAuthentication(authResult); };
		 */
		
		
		
		
		chain.doFilter(request, response);
		
	}

}
