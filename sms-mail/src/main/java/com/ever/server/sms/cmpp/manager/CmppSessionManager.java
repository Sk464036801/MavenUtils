package com.ever.server.sms.cmpp.manager;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;

public abstract class CmppSessionManager {
	
	protected IoSession mtSession;
	protected IoHandlerAdapter mtHandlerClient;
	protected AppContext appContext;
	
	public abstract void Connect();
	protected abstract void initGwHandler();
	protected abstract void initMtHandlerClient();
	
	
	protected void start(){
		this.appContext.setCms(this);
        initGwHandler();
	}
	
	public IoSession getMtSession() {
		return mtSession;
	}
	public void setMtSession(IoSession mtSession) {
		this.mtSession = mtSession;
	}
	public IoHandlerAdapter getMtHandlerClient() {
		return mtHandlerClient;
	}
	public void setMtHandlerClient(IoHandlerAdapter mtHandlerClient) {
		this.mtHandlerClient = mtHandlerClient;
	}
	public AppContext getAppContext() {
		return appContext;
	}
	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}
	
	

}
