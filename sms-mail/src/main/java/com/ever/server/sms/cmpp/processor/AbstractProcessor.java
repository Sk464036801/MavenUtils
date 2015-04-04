package com.ever.server.sms.cmpp.processor;

import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;

public abstract class AbstractProcessor {
	
	private AppContext appContext;
	
	
	abstract void doProcessor(Object message, IoSession session) throws Exception;
	
	

	public AbstractProcessor(AppContext appContext){
		this.appContext = appContext;
	}
	
	public AppContext getAppContext() {
		return appContext;
	}

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}
	
	
	

}
