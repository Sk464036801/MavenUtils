package com.ever.server.sms.cmpp.codec;

public interface Constants {
	
	//sms constants define
	int HEADER_LEN = 12;
	
	int CMPP_CONNECT = 0x00000001;
	int CONNECT_LEN = 27;
	int CMPP_CONNECT_RESP = 0x80000001;
	int CONNECT_RESP_LEN = 18;
	int CMPP_TERMINATE = 0x00000002;
	int CMPP_TERMINATE_RESP = 0x80000002;
	int CMPP_SUBMIT = 0x00000004;
	int SUBMIT_LEN = 147;
	int CMPP_SUBMIT_RESP = 0x80000004;
	int CMPP_ACTIVE_TEST = 0x00000008;
	int CMPP_ACTIVE_TEST_RESP = 0x80000008;
	
	//alarm constants define
	int ALARM_STATE_NORMAL_INTEGER = 0;
	String ALARM_STATE_NOMAL = "已清除";
	int ALARM_STATE_ABNORMAL_INTEGER = 1;
	String ALARM_STATE_ABNORMAL = "产生告警";
	String ALARM_LEVEL_MAJOR = "major";
	int ALARM_LEVEL_MAJOR_INTEGER = 1;
	String ALARM_LEVEL_MINOR = "minor";
	int ALARM_LEVEL_MINOR_INTEGER = 2;
	int ALARM_LEVEL_ALL_INTEGER = 0;
	
	//enable define
	int ENABLE_EMAIL_SEND = 0;
	int ENABLE_SMS_SEND = 0;

    int ALARM_STATE_CLEAR = 0;



}
