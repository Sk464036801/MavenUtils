package com.ever.server.sms.cmpp.processor;

import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;

public class CmppTerminateProcessor extends AbstractProcessor {

	public CmppTerminateProcessor(AppContext appContext) {
		super(appContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	void doProcessor(Object message, IoSession session) throws Exception {
		// TODO Auto-generated method stub

	}

}
