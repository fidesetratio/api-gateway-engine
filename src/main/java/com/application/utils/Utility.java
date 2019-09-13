package com.application.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jboss.logging.Logger;

public class Utility {
	
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static String calculatePath(String url) {
		// TODO Auto-generated method stub;
		String segment[] = url.split("/");

		logger.info("url to calculate:"+url+ " count length = "+segment.length);
		if(segment.length>0 && !url.startsWith("/gwadmin")){
			if(segment.length >=4){
				if(segment[1] != null && segment[2] != null && segment[3] != null){
					String cat=segment[1];
					String urlCalculated = segment[2];
					String urlCatThree = segment[3];
					logger.info("calculate path:"+urlCalculated);
					String det = "/"+cat+"/"+urlCalculated+"/"+urlCatThree+"/**";
					return det;
				};
		};
		}
		return url;
	}
	
	
	public static String calculatePathFront(String url) {
		
		// TODO Auto-generated method stub
		logger.info("url to calculate:"+url);
		String segment[] = url.split("/");
		if(segment.length>0 && !url.startsWith("/gwadmin")){
			String cat=segment[1];
			String urlCalculated = segment[2];
			String urlCatThree = segment[3];
			logger.info("calculate path:"+urlCalculated);
			return "/"+cat+"/"+urlCalculated+"/"+urlCatThree+"/**";
		
		}
		return url;
	}
	public static boolean searchInArray(String[] searchedValue,String content[]){
		boolean res = false;
		Set asSet = new HashSet<>(Arrays.asList(content));
	    int total = 0;
		for(String s:searchedValue){
			 if(asSet.contains(s)){
				 total++;
			 }
		}
		return total==searchedValue.length?true:false;
	}

}
