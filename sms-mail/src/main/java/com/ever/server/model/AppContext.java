package com.ever.server.model;

import org.apache.commons.beanutils.converters.FileConverter;

import com.ever.server.service.OmcService;
import com.ever.server.sms.cmpp.manager.CmppSessionManager;
import com.ever.server.util.FileConfig;
import com.sun.security.auth.login.ConfigFile;

import java.util.ArrayList;
import java.util.List;

public class AppContext {
	
    private int sessionId = 10000000;
    private int SeqId;
	
	// send email
	private String account;
	private String password;
	private String smtp;
	private String fromAddress;
	private String fromName;
	private String infoAddress;
	private int port;
	private String subject;
	private String content;
	private String toAddress;
	private int sleepTime;
	private int enableEmail;
	private int enableSms;
	private int maxThreadPool;
	
	private CmppSessionManager cms;
	
	//sms getway 
	private String gwIp;
	private int gwPort;
	private int connectGwInterval;
	private String gwUser;
	private String gwPassword;
	private int version;
	private int gwIdleTime;
	
	//组播IP和port
	private String multicastIp;
	private int multicastPort;
	
	//OmcService
	private OmcService omcService;
	
	//alarm
	private int alarmLevel;
	private FileConfig config;

    private List<Integer> alarmIdList;
    private String alarmId;
	
    public synchronized int getSessionId() {
        return sessionId++;
    }


    public synchronized int getSeqId() {
        return SeqId++;
    }
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtp() {
		return smtp;
	}
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getInfoAddress() {
		return infoAddress;
	}
	public void setInfoAddress(String infoAddress) {
		this.infoAddress = infoAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}	
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public int getEnableEmail() {
		return enableEmail;
	}
	public void setEnableEmail(int enableEmail) {
		this.enableEmail = enableEmail;
	}
	public int getEnableSms() {
		return enableSms;
	}
	public void setEnableSms(int enableSms) {
		this.enableSms = enableSms;
	}
	public int getMaxThreadPool() {
		return maxThreadPool;
	}
	public void setMaxThreadPool(int maxThreadPool) {
		this.maxThreadPool = maxThreadPool;
	}
	
	
	
	public CmppSessionManager getCms() {
		return cms;
	}
	public void setCms(CmppSessionManager cms) {
		this.cms = cms;
	}
	
	
	public String getGwIp() {
		return gwIp;
	}
	public void setGwIp(String gwIp) {
		this.gwIp = gwIp;
	}
	public int getGwPort() {
		return gwPort;
	}
	public void setGwPort(int gwPort) {
		this.gwPort = gwPort;
	}
	public int getConnectGwInterval() {
		return connectGwInterval;
	}
	public void setConnectGwInterval(int connectGwInterval) {
		this.connectGwInterval = connectGwInterval;
	}
	public String getGwUser() {
		return gwUser;
	}
	public void setGwUser(String gwUser) {
		this.gwUser = gwUser;
	}
	public String getGwPassword() {
		return gwPassword;
	}
	public void setGwPassword(String gwPassword) {
		this.gwPassword = gwPassword;
	}
	public int getGwIdleTime() {
		return gwIdleTime;
	}
	public void setGwIdleTime(int gwIdleTime) {
		this.gwIdleTime = gwIdleTime;
	}
	
	public String getMulticastIp() {
		return multicastIp;
	}
	public void setMulticastIp(String multicastIp) {
		this.multicastIp = multicastIp;
	}
	public int getMulticastPort() {
		return multicastPort;
	}
	public void setMulticastPort(int multicastPort) {
		this.multicastPort = multicastPort;
	}
	
	public OmcService getOmcService() {
		return omcService;
	}
	public void setOmcService(OmcService omcService) {
		this.omcService = omcService;
	}
	
	public int getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


    public String getFileConfig(String name) {
        return config.getConfig(name);
    }

    public void setConfigFile(String configFile) {
        config = new FileConfig(configFile);
    }

    public void setAlarmId(String alarmId) {
        this.alarmId = alarmId;

        alarmIdList = new ArrayList<Integer>();
        String[] alarmArray = alarmId.split(",");
        if (alarmArray == null || alarmArray.length == 0){
            alarmIdList.add(new Integer(24));
            alarmIdList.add(new Integer(25));
        }else{
            for (String item : alarmArray){
                alarmIdList.add(new Integer(item));
            }
        }

    }

    public List<Integer> getAlarmIdList() {
        return alarmIdList;
    }

    @Override
	public String toString() {
		return "AppContext [account=" + account + ", password=" + password
				+ ", smtp=" + smtp + ", fromAddress=" + fromAddress
				+ ", fromName=" + fromName + ", infoAddress=" + infoAddress
				+ ", port=" + port + ", subject=" + subject + ", content="
				+ content + ", toAddress=" + toAddress + ", sleepTime="
				+ sleepTime + ", enableEmail=" + enableEmail + ", enableSms="
				+ enableSms + ", maxThreadPool=" + maxThreadPool + ", cms="
				+ cms + ", gwIp=" + gwIp + ", gwPort=" + gwPort
				+ ", connectGwInterval=" + connectGwInterval + ", gwUser="
				+ gwUser + ", gwPassword=" + gwPassword + ", gwIdleTime="
				+ gwIdleTime + ", multicastIp=" + multicastIp
				+ ", multicastPort=" + multicastPort + "]";
	}




	
	
	
	
	

}
