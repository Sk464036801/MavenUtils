package com.ever.server.sms.cmpp.codec;

import com.ever.server.sms.cmpp.message.*;
import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

public class CmppCodecFactory extends DemuxingProtocolCodecFactory {
	
	public CmppCodecFactory(){
		super.addMessageDecoder(CmppConnectMsgRespDecoder.class);
		super.addMessageDecoder(CmppTerminateRespMsgDecoder.class);
		super.addMessageDecoder(CmppActiveTestMsgRespDecoder.class);
		super.addMessageDecoder(CmppActiveTestMsgDecoder.class);
		super.addMessageDecoder(CmppSubmitRespMsgDecoder.class);
		
		super.addMessageEncoder(CmppConnectMsg.class, CmppConnectMsgEncoder.class);
		super.addMessageEncoder(CmppTerminateMsg.class, CmppTerminateMsgEncoder.class);
		super.addMessageEncoder(CmppActiveTestMsg.class, CmppActiveTestMsgEncoder.class);
        super.addMessageEncoder(CmppActiveTestRespMsg.class, CmppActiveTestMsgRespEncoder.class);
		super.addMessageEncoder(CmppSubmitMsg.class, CmppSubmitMsgEncoder.class);
	}

}
