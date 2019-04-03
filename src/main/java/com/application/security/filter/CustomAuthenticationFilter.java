package com.application.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.application.locator.component.DBUrlComponent;
/**
 * 
 * @author Patar Timotius
 * 
 * Since no plugin for these authentication, we need to create one.
 * it is extended from OncePerRequestFilter
 *
 */
@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter{
	
	 private Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);
    
	 @Autowired
	 private DBUrlComponent dbUrlComponent;
	 
	
	
	private  RemoteTokenServices remoteTokenServices;
	 public CustomAuthenticationFilter(RemoteTokenServices remoteTokenServices) {
		// TODO Auto-generated constructor stub
		 this.remoteTokenServices = remoteTokenServices;
	}
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String headerAuthorization = request.getHeader("Authorization");
		if(headerAuthorization != null )
		{
			
			
			if(headerAuthorization.contains("Bearer")) {
			headerAuthorization = headerAuthorization.replaceAll("Bearer","");
			headerAuthorization = headerAuthorization.trim();
			logger.info("header:"+headerAuthorization);
			logger.info("url : "+request.getRequestURL());
			try {
	
				OAuth2Authentication oauth2Authentication = remoteTokenServices.loadAuthentication(headerAuthorization);
				if(oauth2Authentication != null) {
					SecurityContextHolder.getContext().setAuthentication(oauth2Authentication);
				}
			}catch(Exception e) {
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
