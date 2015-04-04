package com.ever.server.sms.cmpp.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppTerminateRespMsg;

public class CmppTerminateRespMsgDecoder extends AbstractMessageDecoder {
	
	

	public CmppTerminateRespMsgDecoder() {
		super(Constants.CMPP_TERMINATE_RESP);
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractMessage decodeBody(IoSession session, IoBuffer in,
			int bodyLen) {
        if (in.remaining() < bodyLen) return null;

		CmppTerminateRespMsg m = new CmppTerminateRespMsg();
		return m;
	}

}
