package com.ever.server.sms.cmpp.processor;

import com.ever.server.sms.cmpp.codec.Constants;
import com.ever.server.sms.cmpp.message.CmppActiveTestRespMsg;
import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;

public class CmppActiveTestProcessor extends AbstractProcessor {

    private AppContext appContext;


	public CmppActiveTestProcessor(AppContext appContext) {
		super(appContext);
		// TODO Auto-generated constructor stub
        this.appContext = appContext;
	}

	@Override
	void doProcessor(Object message, IoSession session) throws Exception {
		// TODO Auto-generated method stub

        CmppActiveTestRespMsg m = new CmppActiveTestRespMsg();
        m.setCommandId(Constants.CMPP_ACTIVE_TEST_RESP);
        m.setSequenceId(appContext.getSeqId());
        m.setReserved((byte)0);
        session.write(m);

	}

}
