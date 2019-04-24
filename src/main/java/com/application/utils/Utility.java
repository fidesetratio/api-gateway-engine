package com.application.utils;

import org.jboss.logging.Logger;

public class Utility {
	
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static String calculatePath(String url) {
		// TODO Auto-generated method stub
		logger.info("url to calculate:"+url);
		String segment[] = url.split("/");
		if(segment.length>0 && !url.startsWith("/gwadmin")){
			if(segment.length ==4){
				if(segment[1] != null && segment[2] != null && segment[3] != null){
					String cat=segment[1];
					String urlCalculated = segment[2];
					String urlCatThree = segment[3];
					logger.info("calculate path:"+urlCalculated);
					return "/"+cat+"/"+urlCalculated+"/"+urlCatThree+"/**";
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


}
