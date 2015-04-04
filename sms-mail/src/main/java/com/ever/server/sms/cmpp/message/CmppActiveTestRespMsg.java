package com.ever.server.sms.cmpp.message;

public class CmppActiveTestRespMsg extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private byte reserved;

    public byte getReserved() {
        return reserved;
    }

    public void setReserved(byte reserved) {
        this.reserved = reserved;
    }
}
