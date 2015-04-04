package com.ever.server.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonUtil {
	
	private static volatile SingletonUtil instance;
	private SingletonUtil(){}
	private int maxThreadPool;
	private ExecutorService executor;
	
	public static SingletonUtil getInstance(){
		if (instance == null){
			synchronized (SingletonUtil.class) {
				if (instance == null)
					instance = new SingletonUtil();
			}
		}
		return instance;
	}
	
	public void initialization(){
		this.executor = Executors.newFixedThreadPool(this.maxThreadPool);
	}

	public ExecutorService getExecutor() {
		return executor;
	}

	public void setMaxThreadPool(int maxThreadPool) {
		this.maxThreadPool = maxThreadPool;
	}
	
	
	

}
