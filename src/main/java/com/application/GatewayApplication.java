package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.application.repo.GatewayConfigRepository;
import com.application.repo.LinkRepository;
//curl -X POST -vu clientapp:123456 http://localhost:8181/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
//curl -X POST -vu clientapp:123456 http://localhost:8989/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
	
//curl -H "Authorization: Bearer e711fb67-4efb-4f08-a1bf-c375d3858381" http://localhost:9090/apim/spaj/api/json/tracking/201811027360"
//curl -H "Authorization: Bearer e711fb67-4efb-4f08-a1bf-c375d3858381" http://localhost:8989/users?page=2"
//curl -H "Authorization: Bearer 4c9c0434-9684-4457-9a9a-94640a9dc1d7" http://localhost:8181/oauth/logout"
//curl -X POST -vu clientapp:123456 http://localhost:8181/oauth/token -d "client_id=clientapp&grant_type=refresh_token&refresh_token=7d9edebb-a9e7-404e-88fa-31e5f0b49529"




@EnableZuulProxy
@SpringBootApplication
@EnableScheduling
public class GatewayApplication implements CommandLineRunner {
	
	
	 public static void main(String[] args) {
	        SpringApplication.run(GatewayApplication.class, args);
	 }

	@Override
	public void run(String... args) throws Exception {
	}
	
}

		