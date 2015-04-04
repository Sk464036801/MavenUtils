package com.ever.server.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ever.server.model.GlcModuleAlarm;

public class OmcService {
	
	private static final Log logger = LogFactory.getLog(OmcService.class);
	
	//private GeneralDAO baseDao;
	
	
	public GlcModuleAlarm getModuleAlarmById(int alarmId, String moduleName) {
		logger.debug("alarmId = " + alarmId + ",moduleName = " + moduleName);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("alarmId", alarmId);
		paramMap.put("moduleName", moduleName);
		GlcModuleAlarm data=null;
		try {
//			data = (GlcModuleAlarm) baseDao.queryForBean("agent.getAlarmById",
//					paramMap, GlcModuleAlarm.class);
		} catch (Exception e) {
			logger.error("getModuleAlarmById error", e);
		}
		return data;
	}

    public void statusCheck() throws Exception{
//        baseDao.queryForInt("status.check", null);
    }

//	public void setBaseDao(GeneralDAO baseDao) {
//		this.baseDao = baseDao;
//	}

}
