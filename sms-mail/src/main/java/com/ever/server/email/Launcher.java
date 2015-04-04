package com.ever.server.email;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Launcher {
	
	public static void main(String[] args) throws Exception {
		PropertyConfigurator.configure("conf/log4j.properties");
		new FileSystemXmlApplicationContext(
				new String[]{"conf/applicationContext.xml","conf/mailContext.xml"});
	}

}
