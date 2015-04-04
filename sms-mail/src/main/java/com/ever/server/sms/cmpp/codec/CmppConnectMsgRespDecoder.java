package com.ever.server.sms.cmpp.codec;

import com.ever.server.sms.cmpp.util.CmppUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppConnectRespMsg;

public class CmppConnectMsgRespDecoder extends AbstractMessageDecoder {
	
	private static final Log logger = LogFactory.getLog(CmppConnectMsgRespDecoder.class);

	public CmppConnectMsgRespDecoder() {
		super(Constants.CMPP_CONNECT_RESP);
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected AbstractMessage decodeBody(IoSession session, IoBuffer in, int bodyLen) {
        logger.debug("remaing = " + in.remaining() + ", bodyLen = " + bodyLen);
		if(in.remaining() < bodyLen){
			return null;
		}
		CmppConnectRespMsg m = new CmppConnectRespMsg();
		try {
            m.setStatus(in.get());
            logger.debug(" remaing = " + in.remaining());
//            in.skip(in.remaining());
            m.setAuthenticatorISMG(in.getString(16,CmppUtil.getISO88591Decoder()));
            m.setVersion(in.get());
		} catch (Exception e) {
			logger.error("CharacterCodingException ->" + Constants.CMPP_CONNECT_RESP, e);
		}


		return m;
	}

}
