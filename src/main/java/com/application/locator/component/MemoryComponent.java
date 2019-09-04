package com.application.locator.component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.logging.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;

@Component
@ConditionalOnProperty(name="cache.memory", havingValue="memory")
public class MemoryComponent implements CacheComponent {
	
	
	private Map<String,Link> urlMapLocator;	
	private Map<Long,AuthenticationProvider> authenticationProviderMap;
	
	private static Logger logger = Logger.getLogger(MemoryComponent.class);

	
	public MemoryComponent(){
		logger.info("MemoryComponent begin..");
		urlMapLocator = new ConcurrentHashMap<String, Link>();
		authenticationProviderMap = new ConcurrentHashMap<Long, AuthenticationProvider>();
	}
	
	@Override
	public void clearUrlMapLocator() {
		// TODO Auto-generated method stub
		this.urlMapLocator.clear();
	}

	@Override
	public void clearAuthenticationProviderMap() {
		// TODO Auto-generated method stub
		this.authenticationProviderMap.clear();
	}

	@Override
	public AuthenticationProvider getProviderById(Long providerId) {
		// TODO Auto-generated method stub
		return this.authenticationProviderMap.get(providerId);
	}

	@Override
	public void putProviderId(Long providerId, AuthenticationProvider provider) {
		// TODO Auto-generated method stub
		this.authenticationProviderMap.put(providerId, provider);
	}

	@Override
	public Link get(String url){
		if(this.urlMapLocator != null){
			return this.urlMapLocator.get(url);
		}
		return null;
	};

	@Override
	public void put(String url,Link link){
		if(this.urlMapLocator != null){
			this.urlMapLocator.put(url, link);
		}
	}

	@Override
	public void showListLink(){
		for(String path:this.urlMapLocator.keySet()){
			System.out.println("="+path);
		}
	}

}
