package com.ever.server.thread;

import com.ever.server.model.AppContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created with IntelliJ IDEA.
 * User: gk
 * Date: 12/4/13
 * Time: 11:24 上午
 * To change this template use File | Settings | File Templates.
 */
public class CmppConnectThread extends Thread {

    private AppContext appContext;
    private static final Log logger = LogFactory.getLog(CmppConnectThread.class);

    public CmppConnectThread(AppContext appContext){
        this.appContext = appContext;
    }

    @Override
    public void run() {

        appContext.getCms().Connect();

        logger.debug("cmpp connect thread finish .");

    }
}
