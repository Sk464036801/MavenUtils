package com.ever.server.sms.cmpp.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;


import com.ever.server.sms.cmpp.message.AbstractMessage;
import com.ever.server.sms.cmpp.message.CmppHeaderMsg;

public abstract class AbstractMessageDecoder implements MessageDecoder {
	
	private final int commandId;
	private boolean readHeader;
	private CmppHeaderMsg headerMsg;

	protected AbstractMessageDecoder(int commandId) {
		this.commandId = commandId;
	}

	@Override
	public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
		
		//当信息头（header）中没有读取时返回需要读取 NEED_DATA 
		if (in.remaining() < Constants.HEADER_LEN) {
			return MessageDecoderResult.NEED_DATA;
		}
		//如果类型和长度匹配返回 OK
		int len,cmd,seq;
		len = in.getInt();
		cmd = in.getInt();
		seq = in.getInt();
		if (commandId == cmd) {
			return MessageDecoderResult.OK;
		}
		//如果不匹配返回NOT OK
		return MessageDecoderResult.NOT_OK;
	}

	@Override
	public MessageDecoderResult decode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// Try to skip header if not read.
		if (!readHeader) {
			headerMsg = new CmppHeaderMsg();
			headerMsg.setTotalLength(in.getInt());
			headerMsg.setCommandId(in.getInt());
			headerMsg.setSequenceId(in.getInt());
			readHeader = false;
		}
		
        // Try to decode body
        AbstractMessage m = decodeBody(session, in, headerMsg.getTotalLength() - Constants.HEADER_LEN);
        if (m == null){
        	return MessageDecoderResult.NEED_DATA;
        }else{
        	readHeader = false;
        }
		
        m.setCommandId(headerMsg.getCommandId());
        m.setSequenceId(headerMsg.getSequenceId());
        m.setTotalLength(headerMsg.getTotalLength());
        
        out.write(m);
		
		return MessageDecoderResult.OK;
	}
	
    /** 
     * 解析消息的body体的信息 
     * @param session 
     * @param in 
     * @return 
     */  
    protected abstract AbstractMessage decodeBody(IoSession session ,IoBuffer in ,int bodyLen);  

}
