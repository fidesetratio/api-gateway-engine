package com.application.utils;

import org.jboss.logging.Logger;

public class Utility {
	
	private static Logger logger = Logger.getLogger(Utility.class);
	
	public static String calculatePath(String url) {
		// TODO Auto-generated method stub
		
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
