package com.ever.server.sms.cmpp.message;

import java.io.Serializable;

public abstract class AbstractMessage implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int totalLength;
	private int commandId;
	private int sequenceId;
	public int getTotalLength() {
		return totalLength;
	}
	public void setTotalLength(int totalLength) {
		this.totalLength = totalLength;
	}
	public int getCommandId() {
		return commandId;
	}
	public void setCommandId(int commandId) {
		this.commandId = commandId;
	}
	public int getSequenceId() {
		return sequenceId;
	}
	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}
	@Override
	public String toString() {
		return "AbstractMessage [totalLength=" + totalLength + ", commandId="
				+ commandId + ", sequenceId=" + sequenceId + "]";
	}
	
	

}
