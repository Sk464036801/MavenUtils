package com.ever.server.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ever.server.model.AppContext;
import com.ever.server.sms.cmpp.codec.Constants;
import com.ever.server.sms.cmpp.message.CmppSubmitMsg;

public class SmsUtil {
	
	private static final Log logger = LogFactory.getLog(SmsUtil.class);
	private static volatile SmsUtil instance;
	private AppContext appContext;
	private SmsUtil(){}
	
	public static SmsUtil getInstance(){
		if(instance == null){
			synchronized (SmsUtil.class) {
				if(instance == null) instance = new SmsUtil();
			}
		}
		return instance;
	}

    public void initConfig(AppContext appContext){
        this.appContext = appContext;
    }
	
	public void sendSms(String content){
		try {
			CmppSubmitMsg mt = new CmppSubmitMsg();
			node2Mt(mt,content);
            if (appContext.getCms().getMtSession() != null)
			    appContext.getCms().getMtSession().write(mt);
			logger.debug("send sms success.");
		} catch (Exception e) {
			logger.error("send sms error", e);
		}
	}
	
	private void node2Mt(CmppSubmitMsg mt, String content){
		mt.setCommandId(Constants.CMPP_SUBMIT);
		mt.setSequenceId(appContext.getSeqId());
		mt.setServiceId(appContext.getFileConfig("service_id"));
        mt.setFeeUserType((byte)Integer.parseInt(appContext.getFileConfig("fee_usertype")));
		mt.setFeeTeminalId(appContext.getFileConfig("fee_terminal_id"));
		mt.setMsgFmt((byte)Integer.parseInt(appContext.getFileConfig("msg_format")));
		mt.setMsgSrc(appContext.getFileConfig("msg_src"));
		mt.setFeeType(appContext.getFileConfig("fee_type"));
		mt.setFeeCode(appContext.getFileConfig("fee_code"));
		mt.setSrcId(appContext.getFileConfig("src_id"));
//		mt.setDestUserTl(Integer.parseInt(appContext.getFileConfig("dest_user_tl")));
		mt.setDestTerminalId(appContext.getFileConfig("dest_terminal_id"));
		mt.setMsgLength(content.length());
		mt.setMsgContent(content);
//		mt.setReserve(appContext.getFileConfig("reserve"));
		mt.setTotalLength(Constants.HEADER_LEN+content.length()+Constants.SUBMIT_LEN);
	}
	
	

}
