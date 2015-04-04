package com.ever.server.sms.cmpp.processor;

import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;

public class CmppConnecctProcessor extends AbstractProcessor {
	
	public CmppConnecctProcessor(AppContext appContext){
		super(appContext);
	}

	@Override
	void doProcessor(Object message, IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
