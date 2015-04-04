package com.ever.server.sms.cmpp.message;

public class CmppConnectRespMsg extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int status;
	private String authenticatorISMG;
	private int version;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAuthenticatorISMG() {
		return authenticatorISMG;
	}
	public void setAuthenticatorISMG(String authenticatorISMG) {
		this.authenticatorISMG = authenticatorISMG;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CmppConnectRespMsg [status=" + status + ", authenticatorISMG="
				+ authenticatorISMG + ", version=" + version + "]";
	}
	
	
	

}
