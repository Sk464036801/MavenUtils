package com.ever.server.sms.cmpp.codec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppConnectMsg;
import com.ever.server.sms.cmpp.util.CmppUtil;

public class CmppConnectMsgEncoder<T extends AbstractMessage> extends AbstractMessageEncoder<T> {
	
	private static final Log logger = LogFactory.getLog(CmppConnectMsgEncoder.class);
	
	public CmppConnectMsgEncoder(){
		super(Constants.CMPP_CONNECT);
	}

	@Override
	protected void encodeBody(IoSession session, T message, IoBuffer out) {
		CmppConnectMsg m = (CmppConnectMsg) message;
		try {
//			out.putString(m.getSourceAddr(), CmppUtil.getISO88591Encoder());
//			out.putString(m.getAuthenticatorSource(),CmppUtil.getUnicodeEncoder());
            putString(out,m.getSourceAddr(),6,CmppUtil.getISO88591Encoder());
            out.put(m.getPassword());
            logger.debug("password length = " + m.getPassword().length);
//            putString(out,m.getAuthenticatorSource(),16,CmppUtil.getISO88591Encoder());
			out.put((byte)m.getVersion());
			out.putInt(m.getTimestamp());
		} catch (Exception e) {
            logger.debug("sharekey length = " + m.getPassword().length);
			logger.error("characterCoding error", e);
		}
		
	}

}
