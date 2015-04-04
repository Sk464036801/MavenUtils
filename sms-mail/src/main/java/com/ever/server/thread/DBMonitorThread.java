package com.ever.server.thread;

import com.ever.server.model.AppContext;
import com.ever.server.model.GlcModuleAlarm;
import com.ever.server.sms.cmpp.codec.Constants;
import com.ever.server.util.EmailUtil;
import com.ever.server.util.SingletonUtil;
import com.ever.server.util.SmsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Created by gk on 12/27/13.
 */
public class DBMonitorThread extends Thread {

    private AppContext appContext;
    private int statusCheckFlag;
    private static final Log logger = LogFactory.getLog(DBMonitorThread.class);

    @Override
    public void run() {

        final GlcModuleAlarm gma = new GlcModuleAlarm();

        while (true){

            try {
                appContext.getOmcService().statusCheck();
                if(statusCheckFlag > 1){
                    logger.info("database connect succ");
                    statusCheckFlag = 0;
                    gma.setAlarmDescription("程序和数据库进程通信存在异常。");
                    gma.setAlarmState(0);
                    sendDBAlarm(gma);
                }

            } catch (Exception e) {

                statusCheckFlag ++;
                if(statusCheckFlag == 1){
                    logger.error("dataBaseConnectListen error", e);
                    logger.info("database connect fail");
                    gma.setAlarmDescription("程序和数据库进程通信存在异常。");
                    gma.setAlarmState(1);
                    sendDBAlarm(gma);
                }

            }

            mySleep(appContext.getSleepTime());

        }

    }


    private void sendDBAlarm(final GlcModuleAlarm gma){
        if (gma != null ) {

            if (gma.getAlarmState() == Constants.ALARM_STATE_CLEAR){
                gma.setAlarmDescription(gma.getAlarmDescription() + "已清除");
            }

            //发送邮件
            sendMail(gma);
            //下发短信
            sendSms(gma);


//            switch (appContext.getAlarmLevel()) {
//                case Constants.ALARM_LEVEL_ALL_INTEGER:
//                    //发送邮件
//                    sendMail(gma);
//                    //下发短信
//                    sendSms(gma);
//                    break;
//                case Constants.ALARM_LEVEL_MAJOR_INTEGER:
//                    if(Constants.ALARM_LEVEL_MAJOR.equals(gma.getAlarmSeverity())){
//                        //发送邮件
//                        sendMail(gma);
//                        //下发短信
//                        sendSms(gma);
//                    }
//                    break;
//                case Constants.ALARM_LEVEL_MINOR_INTEGER:
//                    if(Constants.ALARM_LEVEL_MINOR.equals(gma.getAlarmSeverity())){
//                        //发送邮件
//                        sendMail(gma);
//                        //下发短信
//                        sendSms(gma);
//                    }
//                    break;
//                default:
//                    break;
//            }

        }
    }

    private void sendSms(final GlcModuleAlarm gma) {
        logger.debug("sendSms execute .");
        if(appContext.getEnableSms() == Constants.ENABLE_SMS_SEND){
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
        if(appContext.getEnableEmail() == Constants.ENABLE_EMAIL_SEND){
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

    public void setAppContext(AppContext appContext) {
        this.appContext = appContext;
    }
}
