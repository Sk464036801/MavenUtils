package com.ever.server.sms.cmpp.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.ever.server.sms.cmpp.message.AbstractMessage;

public class CmppActiveTestMsgEncoder<T extends AbstractMessage> extends AbstractMessageEncoder<T> {
	
	

	public CmppActiveTestMsgEncoder() {
		super(Constants.CMPP_ACTIVE_TEST);
	}

	@Override
	protected void encodeBody(IoSession session, T message, IoBuffer out) {
		
		
	}

}
