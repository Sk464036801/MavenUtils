package com.ever.server.sms.cmpp.processor;

import com.ever.server.model.AppContext;
import com.ever.server.sms.cmpp.message.CmppActiveTestRespMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

public class CmppActiveTestRespProcessor extends AbstractProcessor {


    public static final Log logger = LogFactory.getLog(CmppActiveTestRespProcessor.class);

	public CmppActiveTestRespProcessor(AppContext appContext) {
		super(appContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	void doProcessor(Object message, IoSession session) throws Exception {
		// TODO Auto-generated method stub

        CmppActiveTestRespMsg m = (CmppActiveTestRespMsg)message;
        logger.debug("reveived resp active message reserved = " + m.getReserved());

	}

}
