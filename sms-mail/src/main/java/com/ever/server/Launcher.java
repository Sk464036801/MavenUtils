package com.ever.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Launcher {
	
	private static final Log logger = LogFactory.getLog(Launcher.class);
	
	public static void main(String[] args) {
		
		try {
			PropertyConfigurator.configure("conf/log4j.properties");
			new FileSystemXmlApplicationContext(new String[] {
					"conf/applicationContext.xml", "conf/smsMailContext.xml" });
		} catch (Exception e) {
			logger.error("smsMail Launcher error", e);
		}
		
	}

}
