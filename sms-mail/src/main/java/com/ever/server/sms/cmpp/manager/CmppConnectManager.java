package com.ever.server.sms.cmpp.manager;

import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.ever.server.sms.cmpp.codec.CmppCodecFactory;
import com.ever.server.sms.cmpp.processor.CmppSessionHandler;

public class CmppConnectManager extends CmppSessionManager {
	
	private static final Log logger = LogFactory.getLog(CmppConnectManager.class);

	@Override
	public synchronized void Connect() {
		connectMo();
	}

    @Override
	protected void initGwHandler() {
		initMtHandlerClient();
	}

	@Override
	protected void initMtHandlerClient() {
		this.mtHandlerClient = new CmppSessionHandler(this);
	}
	
	private void connectMo(){

		if(this.mtSession == null){
			
			// 创建一个客户端对象
			NioSocketConnector connector = new NioSocketConnector();
			// Configure the service.
			// 设置连接超时时间
			connector.setConnectTimeoutMillis(appContext.getConnectGwInterval());
			// 获取过滤器链工厂
			DefaultIoFilterChainBuilder filterChain = connector.getFilterChain();
			// 添加相应的过滤链
			// 采用自定义的协议编码工厂类过滤器连
			filterChain.addLast("codec", new ProtocolCodecFilter(new CmppCodecFactory()));
			// 添加日志过滤器连
			filterChain.addLast("logger", new LoggingFilter());
			connector.setHandler(this.mtHandlerClient);

//            do {

                try {
                    ConnectFuture future = connector
                            .connect(new InetSocketAddress(
                                    appContext.getGwIp(), appContext
                                    .getGwPort()));

                    future.awaitUninterruptibly();
                    this.mtSession = future.getSession();

                    logger.info("mt connect to gw succ: ip=" + appContext.getGwIp()
                            + ", port=" + appContext.getGwPort());
                } catch (Exception e) {
                    logger.info("mt connect to gw fail: ip=" + appContext.getGwIp()
                            + ", port=" + appContext.getGwPort());
                    logger.error("connect mtSession error", e);

                }



                if (mtSession == null) logger.debug(" mtSession is null.");
                else logger.debug("mtSession is not null");

//                mySleep(appContext.getConnectGwInterval());

//            }while (mtSession == null);



		}
		
	}

	public CmppConnectManager() {
		super();
//		initGwHandler();
	}

    private void mySleep(int second){
        try {
            Thread.sleep(second*1000);
        }catch (Exception e){
            logger.error("cmpp conect fail ",e);
        }

    }

	
	

	

}
