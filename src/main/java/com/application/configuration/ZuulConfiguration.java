package com.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.zuul.filter.PreContentFilter;

/** 
 * 
 * @author Patar Timotius
 * 
 * I hate we are using filter pre configuration, at this time we don't need
 * them
 *
 */

public class ZuulConfiguration {

	
	/*
	 * @Bean public PreContentFilter contentFilter() {
	 * System.out.println("beginn..."); return new PreContentFilter(); }
	 */
}
