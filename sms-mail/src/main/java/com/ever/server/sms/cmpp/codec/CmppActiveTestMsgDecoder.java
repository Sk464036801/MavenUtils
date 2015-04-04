package com.ever.server.sms.cmpp.codec;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppActiveTestMsg;
import com.ever.server.sms.cmpp.message.CmppActiveTestRespMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class CmppActiveTestMsgDecoder extends AbstractMessageDecoder {

    private  static final Log logger = LogFactory.getLog(CmppActiveTestMsgDecoder.class);

	public CmppActiveTestMsgDecoder() {
		super(Constants.CMPP_ACTIVE_TEST);
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {


	}

	@Override
	protected AbstractMessage decodeBody(IoSession session, IoBuffer in, int bodyLen) {
        logger.debug("remaing = " + in.remaining() + ", bodyLen = " + bodyLen);
        if (in.remaining() < bodyLen) return null;

        CmppActiveTestMsg m = new CmppActiveTestMsg();
		return m;
	}

}
