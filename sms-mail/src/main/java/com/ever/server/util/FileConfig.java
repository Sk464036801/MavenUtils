package com.ever.server.util;

import java.util.Properties;
import java.io.FileInputStream;

public class FileConfig  {

    private Properties config;
    private String configFile;
    
    
    public String getConfig(String config_name) {
        return (config.getProperty(config_name));
    }

    public FileConfig() throws Exception {
        this.config = new Properties();
    }

    public FileConfig(String configFile){
        this.config = new Properties();
        this.setConfigFile(configFile);
    }
    
    public void setConfigFile(String configFile) {
        this.configFile = configFile;
        try {
            this.config.load(new FileInputStream(configFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        try {
            this.config.load(new FileInputStream(configFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
