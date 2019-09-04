package com.application.locator.component;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.Link;

public interface CacheComponent {
	
	public void clearUrlMapLocator();
	public void clearAuthenticationProviderMap();
	public AuthenticationProvider getProviderById(Long providerId);
	public void putProviderId(Long providerId,AuthenticationProvider provider);
	public Link get(String url);
	public void put(String url,Link link);
	public void showListLink();
}
