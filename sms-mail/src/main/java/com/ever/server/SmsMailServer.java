package com.ever.server;

import com.ever.server.thread.CmppConnectThread;
import com.ever.server.util.SmsUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ever.server.model.AppContext;
import com.ever.server.thread.MulticastReceivedThread;
import com.ever.server.util.EmailUtil;
import com.ever.server.util.SingletonUtil;

public class SmsMailServer extends Thread {
	
	private static final Log logger = LogFactory.getLog(SmsMailServer.class);
	private AppContext appContext;

	public void setAppContext(AppContext appContext) {
		this.appContext = appContext;
	}
	

	
	private void initConfig(){
		logger.info("smsMailServer startup.");
		logger.info("appContext = "+appContext.toString());
		//初始化发送邮件配置信息
		EmailUtil.getInstance().initConfig(appContext);
        //初始短信配置信息
        SmsUtil.getInstance().initConfig(appContext);
		//初始化线程池
		SingletonUtil.getInstance().setMaxThreadPool(appContext.getMaxThreadPool());
		SingletonUtil.getInstance().initialization();

	}


	@Override
	public void run() {

		try {
			//初始化相关配置信息
			initConfig();
			//启动组播接收线程
			new MulticastReceivedThread(appContext).start();
            //连接短信网关
            new CmppConnectThread(appContext).start();
			
			logger.info("main thread initialization finish .");
		} catch (Exception e) {
			logger.debug("smsEmailServer error->",e);
		}

	}
	
	

}
