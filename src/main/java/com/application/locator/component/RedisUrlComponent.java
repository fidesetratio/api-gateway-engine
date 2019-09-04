package com.application.locator.component;

import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;

@Component
@ConditionalOnProperty(name="cache.memory", havingValue="redis")
public class RedisUrlComponent implements CacheComponent {
	private Map<String,Link> urlMapLocator;	
	private Map<Long,AuthenticationProvider> authenticationProviderMap;

	private static Logger logger = Logger.getLogger(RedisUrlComponent.class);
	private HashOperations hashOperations;
 	
    private RedisTemplate redisTemplate;
	
	
	 @Autowired
	    public RedisUrlComponent(RedisTemplate redisTemplate){
		 	logger.info("RedisComponent begin..");
		    this.redisTemplate = redisTemplate;
	        this.hashOperations = this.redisTemplate.opsForHash();
	    }	
	@Override
	public void clearUrlMapLocator() {
		// TODO Auto-generated method stub
		//this.urlMapLocator.clear();
		this.hashOperations.entries("LINKURL").clear();
	}

	@Override
	public void clearAuthenticationProviderMap() {
		// TODO Auto-generated method stub
		
		this.hashOperations.entries("PROVIDERURL").clear();
	}

	@Override
	public AuthenticationProvider getProviderById(Long providerId) {
		// TODO Auto-generated method stub
		return (AuthenticationProvider) this.hashOperations.get("PROVIDERURL", providerId);
	}

	@Override
	public void putProviderId(Long providerId, AuthenticationProvider provider) {
		hashOperations.put("PROVIDERURL", providerId, provider);
	}

	@Override
	public Link get(String url){
		return (Link) hashOperations.get("LINKURL", url);
	};

	@Override
	public void put(String url,Link link){
		 hashOperations.put("LINKURL", url, link);
	}

	@Override
	public void showListLink(){
		List l = hashOperations.values("LINKURL");
	
	}

}
