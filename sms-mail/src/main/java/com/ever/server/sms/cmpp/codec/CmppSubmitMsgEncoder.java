package com.ever.server.sms.cmpp.codec;

import com.ever.server.sms.cmpp.message.CmppSubmitMsg;
import com.ever.server.sms.cmpp.util.CmppUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.ever.server.sms.cmpp.message.AbstractMessage;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;

public class CmppSubmitMsgEncoder <T extends AbstractMessage> extends AbstractMessageEncoder<T> {

    private static final Log logger = LogFactory.getLog(CmppSubmitMsgEncoder.class);

    public CmppSubmitMsgEncoder(){
		
		super(Constants.CMPP_SUBMIT);
	}

	@Override
	protected void encodeBody(IoSession session, T message, IoBuffer out) {
        CmppSubmitMsg m = (CmppSubmitMsg) message;
        try {
            CharsetEncoder iso88591Encoder = CmppUtil.getISO88591Encoder();
            byte tmpbyte = 1;

            out.fill(8); //msg_id
            out.put(tmpbyte);// pkt total 1
            out.put(tmpbyte);// pkt number 1
            out.put((byte)0);//msg_registered_delivery
            out.fill(1); // msg_level
            putString(out, m.getServiceId(), 10, iso88591Encoder);
            out.put(m.getFeeUserType());  //fee_user_type
            putString(out, m.getFeeTeminalId(), 21, iso88591Encoder);
            out.fill(2);
            out.put(m.getMsgFmt());
            putString(out, m.getMsgSrc(), 6, iso88591Encoder);
            putString(out, m.getFeeType(), 2, iso88591Encoder);
            putString(out, m.getFeeCode(), 6, iso88591Encoder);
            out.fill(34);  //valid_tiem at_time
            putString(out, m.getSrcId(), 21, iso88591Encoder);
            tmpbyte = 1;
            out.put(tmpbyte);
            putString(out, m.getDestTerminalId(), 21, iso88591Encoder);
            int msgPos = out.position();
            out.fill(1);
            CharsetEncoder contentEncoder = CmppUtil.getCharsetEncoder(m
                    .getMsgFmt());
            putString(out, m.getMsgContent(), -1, contentEncoder);
            int newPos = out.position();
            int msgLen = newPos - msgPos - 1;
            out.position(msgPos);
            out.put((byte) msgLen);
            out.position(newPos);
            out.fill(8);

        } catch (Exception e) {
            logger.error("CharacterCodingException error", e);
        }



	}

}
