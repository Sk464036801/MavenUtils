package com.ever.server.sms.cmpp.message;

public class CmppSubmitMsg extends AbstractMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int msgId;
	private int pkTotal;
	private int pkNumber;
	private int registeredDelivery;
	private int msgLevel;
	private String serviceId;
	private byte feeUserType;
	private String feeTeminalId;
	private int tpPid;
	private int tpUdhi;
	private byte msgFmt;
	private String msgSrc;
	private String feeType;
	private String feeCode;
	private String validTime;
	private String atTime;
	private String srcId;
	private int destUserTl;
	private String destTerminalId;
	private int msgLength;
	private String msgContent;
	private String reserve;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getPkTotal() {
		return pkTotal;
	}
	public void setPkTotal(int pkTotal) {
		this.pkTotal = pkTotal;
	}
	public int getPkNumber() {
		return pkNumber;
	}
	public void setPkNumber(int pkNumber) {
		this.pkNumber = pkNumber;
	}
	public int getRegisteredDelivery() {
		return registeredDelivery;
	}
	public void setRegisteredDelivery(int registeredDelivery) {
		this.registeredDelivery = registeredDelivery;
	}
	public int getMsgLevel() {
		return msgLevel;
	}
	public void setMsgLevel(int msgLevel) {
		this.msgLevel = msgLevel;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public byte getFeeUserType() {
		return feeUserType;
	}
	public void setFeeUserType(byte feeUserType) {
		this.feeUserType = feeUserType;
	}
	public String getFeeTeminalId() {
		return feeTeminalId;
	}
	public void setFeeTeminalId(String feeTeminalId) {
		this.feeTeminalId = feeTeminalId;
	}
	public int getTpPid() {
		return tpPid;
	}
	public void setTpPid(int tpPid) {
		this.tpPid = tpPid;
	}
	public int getTpUdhi() {
		return tpUdhi;
	}
	public void setTpUdhi(int tpUdhi) {
		this.tpUdhi = tpUdhi;
	}
	public byte getMsgFmt() {
		return msgFmt;
	}
	public void setMsgFmt(byte msgFmt) {
		this.msgFmt = msgFmt;
	}
	public String getMsgSrc() {
		return msgSrc;
	}
	public void setMsgSrc(String msgSrc) {
		this.msgSrc = msgSrc;
	}
	public String getFeeType() {
		return feeType;
	}
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	public String getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}
	public String getValidTime() {
		return validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public String getAtTime() {
		return atTime;
	}
	public void setAtTime(String atTime) {
		this.atTime = atTime;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public int getDestUserTl() {
		return destUserTl;
	}
	public void setDestUserTl(int destUserTl) {
		this.destUserTl = destUserTl;
	}
	public String getDestTerminalId() {
		return destTerminalId;
	}
	public void setDestTerminalId(String destTerminalId) {
		this.destTerminalId = destTerminalId;
	}
	public int getMsgLength() {
		return msgLength;
	}
	public void setMsgLength(int msgLength) {
		this.msgLength = msgLength;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CmppSubmitMsg [msgId=" + msgId + ", pkTotal=" + pkTotal
				+ ", pkNumber=" + pkNumber + ", registeredDelivery="
				+ registeredDelivery + ", msgLevel=" + msgLevel
				+ ", serviceId=" + serviceId + ", feeUserType=" + feeUserType
				+ ", feeTeminalId=" + feeTeminalId + ", tpPid=" + tpPid
				+ ", tpUdhi=" + tpUdhi + ", msgFmt=" + msgFmt + ", msgSrc="
				+ msgSrc + ", feeType=" + feeType + ", feeCode=" + feeCode
				+ ", validTime=" + validTime + ", atTime=" + atTime
				+ ", srcId=" + srcId + ", destUserTl=" + destUserTl
				+ ", destTerminalId=" + destTerminalId + ", msgLength="
				+ msgLength + ", msgContent=" + msgContent + ", reserve="
				+ reserve + "]";
	}
	
	
	

}
