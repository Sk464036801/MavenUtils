package com.ever.server.sms.cmpp.codec;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public class CmppActiveTestMsgRespEncoder<T extends AbstractMessage> extends AbstractMessageEncoder<T> {



	public CmppActiveTestMsgRespEncoder() {
		super(Constants.CMPP_ACTIVE_TEST_RESP);
	}

	@Override
	protected void encodeBody(IoSession session, T message, IoBuffer out) {

        out.fill(1); //padding 1 byte
		
	}

}
