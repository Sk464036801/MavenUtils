package com.ever.server.sms.cmpp.codec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppSubmitRespMsg;

public class CmppSubmitRespMsgDecoder extends AbstractMessageDecoder {

    public static final Log logger = LogFactory.getLog(CmppSubmitRespMsgDecoder.class);

	public CmppSubmitRespMsgDecoder() {
		super(Constants.CMPP_SUBMIT_RESP);
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractMessage decodeBody(IoSession session, IoBuffer in,
			int bodyLen) {
        logger.debug("remaing = " + in.remaining() + ", bodyLen = " + bodyLen);
		if(in.remaining() < bodyLen){
			return null;
		}
		CmppSubmitRespMsg m = new CmppSubmitRespMsg();

        try {
            m.setMsgId(in.getLong());
            m.setResult(in.get());
//            in.skip(in.remaining());
            logger.debug(" remaing = " + in.remaining());
        } catch (Exception e) {
          logger.error(Constants.CMPP_SUBMIT_RESP + " error:",e);
        }
        return m;
	}

}
