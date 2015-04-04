package com.ever.server.sms.cmpp.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import com.ever.server.model.AppContext;
import com.ever.server.sms.cmpp.codec.Constants;
import com.ever.server.sms.cmpp.manager.CmppSessionManager;
import com.ever.server.sms.cmpp.message.CmppActiveTestMsg;
import com.ever.server.sms.cmpp.message.CmppActiveTestRespMsg;
import com.ever.server.sms.cmpp.message.CmppConnectMsg;
import com.ever.server.sms.cmpp.message.CmppConnectRespMsg;
import com.ever.server.sms.cmpp.message.CmppSubmitRespMsg;
import com.ever.server.sms.cmpp.message.CmppTerminateRespMsg;
import com.ever.server.sms.cmpp.util.CmppUtil;

public class CmppSessionHandler extends IoHandlerAdapter {
	
	private static final Log logger = LogFactory.getLog(CmppSessionHandler.class);
	
	private Hashtable<Integer, AbstractProcessor> ht;
	private AppContext appContext;
	private CmppSessionManager csm;
	
    private void register(int cmd, AbstractProcessor psr) {
        ht.put(new Integer(cmd), psr);
    }

    private AbstractProcessor findProcesser(int msghashcode) {
        return (AbstractProcessor) (ht.get(new Integer(msghashcode)));
    }
    
    private void init() {
        ht = new Hashtable<Integer, AbstractProcessor>();
        register(CmppActiveTestRespMsg.class.hashCode(), new CmppActiveTestRespProcessor(appContext));
        register(CmppActiveTestMsg.class.hashCode(), new CmppActiveTestProcessor(appContext));
        register(CmppConnectRespMsg.class.hashCode(), new CmppConnecctProcessor(appContext));
        register(CmppTerminateRespMsg.class.hashCode(),new CmppTerminateProcessor(appContext));
        register(CmppSubmitRespMsg.class.hashCode(),new CmppSubmitProcessor(appContext));
       
    }

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		session.close(true);
		logger.error(cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		logger.debug("recv message : " + message.getClass().getName());
		logger.debug("findProcessor :" + findProcesser(message.getClass().hashCode()));
		
		try {
			AbstractProcessor processor = findProcesser(message.getClass().hashCode());
			processor.doProcessor(message, session);
		} catch (Exception e) {
			logger.error("messageReceived error", e);
			session.close(true);
		}
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		if(this.csm.getMtSession().equals(session)){
			this.csm.setMtSession(null);
		}else{
			this.csm.setMtSession(null);
		}
		
		logger.error("connection close,reconnect");
		this.csm.Connect();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		CmppActiveTestMsg activeTest = new CmppActiveTestMsg();
        activeTest.setTotalLength(Constants.HEADER_LEN);
        activeTest.setCommandId(Constants.CMPP_ACTIVE_TEST);
		activeTest.setSequenceId(appContext.getSeqId());

		session.write(activeTest);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {

		CmppConnectMsg connect = new CmppConnectMsg();
		connect.setTotalLength(Constants.HEADER_LEN + Constants.CONNECT_LEN);
		connect.setCommandId(Constants.CMPP_CONNECT);
		connect.setSequenceId(appContext.getSeqId());
		connect.setSourceAddr(appContext.getGwUser());
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
        Date date = new Date();
        String dateStr = dateFormat.format(date);

        logger.debug("user = " + appContext.getGwUser() + ", password = " + appContext.getGwPassword());
        try {
            connect.setPassword(CmppUtil.genEncPassword(
                    appContext.getGwUser(), appContext.getGwPassword(), dateStr));

        } catch (Exception e) {
            logger.error("get password error",e);
            connect.setAuthenticatorSource(appContext.getGwPassword());
        }
        connect.setVersion(appContext.getVersion());
		connect.setTimestamp(Integer.parseInt(dateStr));

		session.write(connect);
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, appContext.getGwIdleTime());
	}

	public CmppSessionHandler(CmppSessionManager csm) {
		this.csm = csm;
		this.appContext = csm.getAppContext();
		init();
	}





	
	
	

}
