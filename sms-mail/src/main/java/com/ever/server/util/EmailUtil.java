package com.ever.server.util;


import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.mail.MultiPartEmail;

import com.ever.server.model.AppContext;

public class EmailUtil {
	
	private static volatile EmailUtil instance;
	private static Log logger = LogFactory.getLog(EmailUtil.class);
	
	private EmailUtil(){}
	
	private MultiPartEmail email=null;
	private String contentTemplate = "";
	
	public static EmailUtil getInstance(){
		if(instance == null){
			synchronized (EmailUtil.class) {
				
				if(instance == null)
					instance = new EmailUtil();
			}
		}
		return instance;
	}
	
	public void initConfig(AppContext context) {
		try {
			this.email = new MultiPartEmail();
			this.email.setHostName(context.getSmtp());
			this.email.setSmtpPort(context.getPort());
			this.email.setAuthentication(context.getAccount(),
					context.getPassword());
			this.email.setSubject(context.getSubject());
			this.email.setFrom(context.getFromAddress(), context.getFromName());
			String toa = context.getToAddress();
			if (toa == null || toa.length() == 0)
				toa = "shangke@evermobilesoft.com";
			String[] addr = toa.split(",");
			for (int i = 0; i < addr.length; i++)
				this.email.addTo(addr[i]);

			this.contentTemplate = context.getContent();

		} catch (Exception e) {
			logger.error("init mail config error", e);
		}
	}
	
	public boolean sendEmail(String context){
		try {
			
			String data = this.contentTemplate;
			BodyPart mdp = new MimeBodyPart();//新建一个存放信件内容的BodyPart对象
			mdp.setContent(data.replace("[#reply#]", context), "text/html;charset=gb2312"); //给BodyPart对象设置内容和格式/编码方式 
			
		    MimeMultipart part = new MimeMultipart(); //新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个) 
		    part.addBodyPart(mdp); //将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			
			email.setContent(part);

			String res = email.send();
			logger.info("send mail success,res=" + res);
		} catch (Exception e) {
			logger.error("sendEmail error", e);
			return false;
		}
		return true;
	}
	
	

}
