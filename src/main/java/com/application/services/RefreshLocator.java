package com.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.application.locator.DatabaseRouteLocator;
  
@Service
public class RefreshLocator {
	
	@Autowired
    private ApplicationEventPublisher publisher;
 
    @Autowired
    private DatabaseRouteLocator routeLocator;
 
    public void refreshRoute() {
        RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
        publisher.publishEvent(routesRefreshedEvent);
    }


}
