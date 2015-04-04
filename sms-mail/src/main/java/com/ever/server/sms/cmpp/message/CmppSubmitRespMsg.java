package com.ever.server.sms.cmpp.message;

public class CmppSubmitRespMsg extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long msgId;
	private byte result;
	public long getMsgId() {
		return msgId;
	}
	public void setMsgId(long msgId) {
		this.msgId = msgId;
	}
	public byte getResult() {
		return result;
	}
	public void setResult(byte result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "CmppSubmitRespMsg [msgId=" + msgId + ", result=" + result + "]";
	}
	
	

}
