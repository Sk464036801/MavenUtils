package com.ever.server.model;

import java.util.Date;

public class GlcModuleAlarm {

	  
	private int alarmId;
	private String alarmType;
	private String moduleName;
	private String alarmSeverity;
	private Date alarmOccurTime;
	private int alarmState;
	private String alarmDescription;
	private int ifReport;

	
	public int getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getAlarmSeverity() {
		return alarmSeverity;
	}
	public void setAlarmSeverity(String alarmSeverity) {
		this.alarmSeverity = alarmSeverity;
	}
	public Date getAlarmOccurTime() {
		return alarmOccurTime;
	}
	public void setAlarmOccurTime(Date alarmOccurTime) {
		this.alarmOccurTime = alarmOccurTime;
	}
	public int getAlarmState() {
		return alarmState;
	}
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	public String getAlarmDescription() {
		return alarmDescription;
	}
	public void setAlarmDescription(String alarmDescription) {
		this.alarmDescription = alarmDescription;
	}
	
	public int getIfReport() {
		return ifReport;
	}
	public void setIfReport(int ifReport) {
		this.ifReport = ifReport;
	}
	@Override
	public String toString() {
		return "GlcModuleAlarm [alarmId=" + alarmId + ", alarmType="
				+ alarmType + ", moduleName=" + moduleName + ", alarmSeverity="
				+ alarmSeverity + ", alarmOccurTime=" + alarmOccurTime
				+ ", alarmState=" + alarmState + ", alarmDescription="
				+ alarmDescription + ", ifReport=" + ifReport + "]";
	}
	
	
	
}
