package com.application.locator.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;

@Component
public class DBUrlComponent {
	
	private Map<String,Link> urlMapLocator;	
	private Map<Long,AuthenticationProvider> authenticationProviderMap;
	
	public DBUrlComponent(){
		urlMapLocator = new ConcurrentHashMap<String, Link>();
		authenticationProviderMap = new ConcurrentHashMap<Long, AuthenticationProvider>();
		
	}
	
	
	public AuthenticationProvider getProviderById(Long providerId) {
		return this.authenticationProviderMap.get(providerId);
	}
	
	public void putProviderId(Long providerId,AuthenticationProvider provider) {
			this.authenticationProviderMap.put(providerId, provider);
	}
	
	public Link get(String url){
		if(this.urlMapLocator != null){
			return this.urlMapLocator.get(url);
		}
		return null;
	};
	
	public void put(String url,Link link){
		if(this.urlMapLocator != null){
			this.urlMapLocator.put(url, link);
		}
	}
}
