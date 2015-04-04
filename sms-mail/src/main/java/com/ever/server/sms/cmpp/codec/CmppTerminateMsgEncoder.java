package com.ever.server.sms.cmpp.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.ever.server.sms.cmpp.message.AbstractMessage;

public class CmppTerminateMsgEncoder <T extends AbstractMessage> extends AbstractMessageEncoder<T> {	

	public CmppTerminateMsgEncoder() {
		super(Constants.CMPP_TERMINATE);
	}

	@Override
	protected void encodeBody(IoSession session, T message, IoBuffer out) {
		// TODO Auto-generated method stub
		
	}

}
