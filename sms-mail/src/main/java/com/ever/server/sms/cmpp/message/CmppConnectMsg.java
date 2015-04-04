package com.ever.server.sms.cmpp.message;

public class CmppConnectMsg extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sourceAddr;
	private String authenticatorSource;
	private int version;
	private int timestamp;
    private byte[] password;
	public String getSourceAddr() {
		return sourceAddr;
	}
	public void setSourceAddr(String sourceAddr) {
		this.sourceAddr = sourceAddr;
	}
	public String getAuthenticatorSource() {
		return authenticatorSource;
	}
	public void setAuthenticatorSource(String authenticatorSource) {
		this.authenticatorSource = authenticatorSource;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    @Override
	public String toString() {
		return "CmppConnectMsg [sourceAddr=" + sourceAddr
				+ ", authenticatorSource=" + authenticatorSource + ", version="
				+ version + ", timestamp=" + timestamp + "]";
	}
	
	
	
	

}
