package com.application.locator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.stereotype.Service;

import com.app.manager.model.AuthenticationProvider;
import com.app.manager.model.GatewayConfig;
import com.app.manager.model.Link;
import com.application.locator.component.CacheComponent;
import com.application.locator.component.DBUrlComponent;
import com.application.repo.AuthenticationProviderRepository;
import com.application.services.GatewayService;
import com.application.services.LinkService;

/**
 * 
 * @author Patar Timotius
 * 
 * Database Route Locator based on RefreshRouteLocator, but we need to extend it 
 * to read from database
 *
 */

@Service
@ConditionalOnProperty(name="gateway.locator", havingValue="db")
public class DatabaseRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator{

	
	 private Map<String,ZuulRoute> mapsOfLocator;	
	
	 @Autowired
	 private LinkService linkService;
	 
	 
	 @Autowired
	 private CacheComponent dbUrlComponent;
	 
	 
	 
	 @Autowired
	 private GatewayService gatewayService;
	 
	 @Autowired
	 private AuthenticationProviderRepository authenticationProviderRepo;
	 
	
	 private Logger logger = LoggerFactory.getLogger(DatabaseRouteLocator.class);
	    
	 public DatabaseRouteLocator(ServerProperties server, ZuulProperties properties) {
			super(server.getServlet().getContextPath(), properties);
			mapsOfLocator= new LinkedHashMap<String,ZuulRoute>();
 }

	 
		
	@Override
	public void refresh() {
		doRefresh();
	}
	
	
	protected Map<String, ZuulRoute> locateRoutes() {
		logger.info("Database Router...");
		LinkedHashMap<String, ZuulRoute> routesMap = new LinkedHashMap<String, ZuulRoute>();
		List<Link> ls =  new ArrayList<Link>();
		Map<String,ZuulRoute> oldRoute = super.locateRoutes();
		mapsOfLocator.clear();
		for(Link l:linkService.getActiveLinks()) {
				ZuulRoute zuulRoute = new ZuulRoute();
				String id = Long.toString(l.getLinkId())+"-"+l.getServiceId();
				id = l.getServiceId();
				zuulRoute.setId(id);
				zuulRoute.setPath(l.getPath());
				zuulRoute.setUrl(l.getUrl());
				zuulRoute.setStripPrefix(l.isStripPrefix());
				if(l.getSensitiveHeaders().size()>0) {
					Set<String> head = new HashSet<String>();
					for(String s:l.getSensitiveHeaders()) {
						head.add(s.trim());
					}
					zuulRoute.setSensitiveHeaders(head);
				}
			/*
			 * zuulRoute.setStripPrefix(false); Set<String> head = new HashSet<String>();
			 * head.add("Cookie"); head.add("Set-Cookie");
			 * zuulRoute.setSensitiveHeaders(head);
			 */	String p = l.getServiceId();
				mapsOfLocator.put(l.getPath(),zuulRoute);
		}
		
		
		
		for(String k:mapsOfLocator.keySet()) {
			logger.info("route k="+k+":"+mapsOfLocator.get(k));
		}
		
		this.reloadNewUrl();
		this.reloadProviderAuthentication();
		this.reloadConfig();
		return mapsOfLocator;
	}
	
	
	
	
	private void reloadNewUrl() {
		logger.info("reloadNewUrl is new url ");
   		dbUrlComponent.clearUrlMapLocator();
		List<Link> newchange = linkService.getActiveLinks();
   		logger.info("size: "+newchange.size());
   		List<Link> ls =  new ArrayList<Link>();
		if(newchange.size()>0) {
			for(Link l : newchange){
   				logger.info("l"+l.getRoles()+" :: "+l.isPermitAll());
   				logger.info("pathl:"+l.getPath());
   				dbUrlComponent.put(l.getPath().trim(), l);
   			};
   		};
   	}
 	
 	
	private void reloadConfig() {
   		List<GatewayConfig> newchange = gatewayService.getOnlyNew();
    	List<GatewayConfig> ls =  new ArrayList<GatewayConfig>();
		if(newchange.size()>0) {
   			 
   			for(GatewayConfig l : newchange){
				/*
				 * l.setNew(false);
				 * 
				 * if(l.getKey().trim().equals("gateway.locator.prop.remote.token.clientid")){
				 * tokenService.setClientId(l.getValue()); }
				 * if(l.getKey().trim().equals("gateway.locator.prop.remote.token.clientsecret")
				 * ){ tokenService.setClientSecret(l.getValue()); }
				 * 
				 * if(l.getKey().trim().equals("gateway.locator.prop.remote.token.services")){
				 * tokenService.setCheckTokenEndpointUrl(l.getValue()); } ls.add(l);
				 */
   			};
   			if(ls.size()>0)
   				gatewayService.update(ls);
   		};
   	}
	
	
	private void reloadProviderAuthentication() {
		logger.info("reload reloadProviderAuthentication....");
		dbUrlComponent.clearAuthenticationProviderMap();
		List<AuthenticationProvider> providerList = authenticationProviderRepo.findByActiveTrue();
		for(AuthenticationProvider provider:providerList) {
			logger.info("reload providerId...."+provider.getProviderName());
			dbUrlComponent.putProviderId(provider.getProviderId(),provider);
		}
	}
	   			
	

}
