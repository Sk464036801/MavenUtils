package com.ever.server.thread;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.ever.server.util.SmsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ever.server.model.AppContext;
import com.ever.server.model.GlcModuleAlarm;
import com.ever.server.sms.cmpp.codec.Constants;
import com.ever.server.util.EmailUtil;
import com.ever.server.util.SingletonUtil;
import com.ever.server.util.StringUtil;

public class MulticastReceivedThread extends Thread {
	
	private static final Log logger = LogFactory.getLog(MulticastReceivedThread.class);
	private AppContext appContext;
	private MulticastSocket socket;
	

	@Override
	public void run() {
		this.initialization();
		this.receiveMessage();
        logger.info("multicast receive message finished ..");
	}
	
	private void initialization(){
		logger.debug("multicast ip : " + appContext.getMulticastIp() + 
				", port = "+appContext.getMulticastPort());
		
		try {
			// 创建组播地址对象
			InetAddress address = InetAddress.getByName(appContext
					.getMulticastIp());
			// 创建组播Socket对象,在特定的端口上接收数据
			socket = new MulticastSocket(appContext.getMulticastPort());
			// 加入到组中，才可以接收到消息
			socket.joinGroup(address);
		} catch (Exception e) {
			logger.debug("initialization multicast socket error", e);
		}
		
	}
	
	private void receiveMessage(){
		while(true){
			byte[] date = new byte[1024];
			try {
				// 接收信息的数据包对象
				DatagramPacket packet = new DatagramPacket(date, date.length);
                logger.debug("date length:" + date.length);
                socket.receive(packet);

				String cmd=(new String(date).trim());//updateconfig:key=value;key=value;
				logger.debug("recv cmd :"+cmd);
				logger.info(packet.getAddress()+"  multicastReceived  cmd:"+cmd);
				
				if(!StringUtil.isEmpty(cmd)){
					String []head=cmd.split(":");
					if(!StringUtil.isEmpty(head)){
						//omc server入库，通知
						alarmFromDB(head);
						
					}
				}
				
			} catch (Exception e) {
				logger.error("receive message error", e);
			}
		}
	}
	
	private void alarmFromDB(String[] head) {
		//omc server入库，通知agent
		if(head[0].equals("getalarmfromdb")){
			logger.debug("recv length = " + head.length);
			try {
				if(head.length < 3) return;
				String appIndex = head[2].split("_")[1];
				logger.debug("appIndex = "+appIndex);
				
				if (!StringUtil.isEmpty(head[1])) {

                    for (Integer item : appContext.getAlarmIdList()){
                        if (head[1].equals(item)) return;
                    }

					final GlcModuleAlarm gma = appContext.getOmcService()
							.getModuleAlarmById(
									Integer.parseInt(head[1]),head[2]);
					logger.debug("enable report:"
							+ gma.getIfReport() + "alarm level = " + appContext.getAlarmLevel());
					
					if (gma != null ) {

                        gma.setAlarmDescription("App_"+appIndex + ", " + gma.getAlarmDescription());
                        if (gma.getAlarmState() == Constants.ALARM_STATE_CLEAR){
                            gma.setAlarmDescription(gma.getAlarmDescription() + "已清除");
                        }

						
						switch (appContext.getAlarmLevel()) {
						case Constants.ALARM_LEVEL_ALL_INTEGER:
							//发送邮件
							sendMail(gma);
							//下发短信
							sendSms(gma);
							break;
						case Constants.ALARM_LEVEL_MAJOR_INTEGER:
							if(Constants.ALARM_LEVEL_MAJOR.equals(gma.getAlarmSeverity())){
								//发送邮件
								sendMail(gma);
								//下发短信
								sendSms(gma);
							}
							break;
						case Constants.ALARM_LEVEL_MINOR_INTEGER:
							if(Constants.ALARM_LEVEL_MINOR.equals(gma.getAlarmSeverity())){
								//发送邮件
								sendMail(gma);
								//下发短信
								sendSms(gma);
							}
							break;
						default:
							break;
						}
						
					}
				}
			} catch (Exception e) {
				logger.error("alarmFromDB error",e);
			}
		}
	}

	private void sendSms(final GlcModuleAlarm gma) {
        logger.debug("sendSms execute .");
		if(appContext.getEnableSms() == Constants.ENABLE_SMS_SEND &&
                gma.getIfReport() == com.ever.server.model.Constants.ALARM_SEND){
			SingletonUtil.getInstance().getExecutor().submit(new Runnable() {
				
				@Override
				public void run() {

                    if (appContext.getCms().getMtSession() == null){
                        logger.debug("session is null ...");
                        appContext.getCms().Connect();
//                        myWait(10);
                    }
                    else{
                        logger.debug("session is not null ..");
                        SmsUtil.getInstance().sendSms(gma.getAlarmDescription());
                    }

                }
            });
		}
	}

	private void sendMail(final GlcModuleAlarm gma) {
        logger.debug("send mail execute .");
		if(appContext.getEnableEmail() == Constants.ENABLE_EMAIL_SEND &&
                gma.getIfReport() == com.ever.server.model.Constants.ALARM_SEND){
			SingletonUtil.getInstance().getExecutor().submit(new Runnable() {

				@Override
				public void run() {
					EmailUtil.getInstance()
							.sendEmail(gma.getAlarmDescription());

				}
			});
		}
	}
	
	private void mySleep(int sec) {

		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			logger.error("smsMailServer Error", e);
		}
		
		logger.info("Thead sleep .....");
	}

    private void myWait(int sec){
        try {
            Thread.currentThread().wait(sec*1000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException error",e);
        }
    }


	public MulticastReceivedThread(AppContext appContext) {
		super();
		this.appContext = appContext;
	}
	
	
}
