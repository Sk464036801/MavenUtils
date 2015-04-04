package com.ever.server.email;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ever.server.model.AppContext;

public class MailServer extends Thread {
	
	private static final Log logger = LogFactory.getLog(MailServer.class);
	private AppContext appContext;
	

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}


	@Override
	public void run() {
		
		logger.info("Mail Server startup.");
		
	}
	
	

}
