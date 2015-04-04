package com.ever.server.sms.cmpp.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.ever.server.sms.cmpp.message.AbstractMessage;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetEncoder;

public abstract class AbstractMessageEncoder<T extends AbstractMessage> implements MessageEncoder<T> {
	
	private final int commandId;

	protected AbstractMessageEncoder(int commandId) {		
		this.commandId = commandId;
	}

	@Override
	public void encode(IoSession session, T message, ProtocolEncoderOutput out)
			throws Exception {
		
		IoBuffer buf = IoBuffer.allocate(128);
		//Enable auto-expand for easier encoding
		buf.setAutoExpand(true);
		
		//Message Header
		AbstractMessage m = (AbstractMessage)message;
		int pktStartPos = buf.position();
		buf.putInt(m.getTotalLength());
		buf.putInt(this.commandId);
		buf.putInt(m.getSequenceId());
		
		//Message Body
		int bodyStartPos = buf.position();
		encodeBody(session, message, buf);
		int bodyLen = buf.position() - bodyStartPos;
		int totalLen = Constants.HEADER_LEN +bodyLen;
		int pktEndPos = buf.position();
		buf.position(pktStartPos);
		buf.putInt(totalLen);
		buf.position(pktEndPos);
		
		buf.flip();
		out.write(buf);
		
		
	}
	
    /** 
     * 解析消息的体的方法的 
     * @param session 
     * @param message 
     * @param out 
     */  
    protected abstract void encodeBody(IoSession session ,T message ,IoBuffer out);

    protected void putString(IoBuffer out, String s, int maxLen,
                             CharsetEncoder encoder) {

        if (s == null || s.length() == 0) {
            out.fill(maxLen);
            return;
        }

        int pos = out.position();
        int end = pos + maxLen;

        try {
            if (maxLen < 0)
                out.putString(s, encoder);
            else
                out.putString(s, maxLen, encoder);
        } catch (CharacterCodingException e) {
            int left = end - out.position();
            if (left > 0)
                out.fill(left);
        }

    }

}
