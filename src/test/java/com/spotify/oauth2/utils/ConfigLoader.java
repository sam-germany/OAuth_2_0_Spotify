package com.spotify.oauth2.utils;

import java.util.Properties;

public class ConfigLoader {

    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        properties = PropertiesUtils.propertyLoader("src/test/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if(configLoader == null) {
            configLoader = new ConfigLoader();
        }

        return configLoader;
    }

    public String getClientId22() {
        String prop = properties.getProperty("client_id");
        if(prop != null){
            return  prop;
        }else{
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }

    public String getClientSecret22() {
        String prop = properties.getProperty("client_secret");
        if(prop != null){
            return  prop;
        }else{
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }
    public String getRefreshToken22() {
        String prop = properties.getProperty("refresh_token");
        if(prop != null){
            return  prop;
        }else{
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }
    public String getGrantType22() {
        String prop = properties.getProperty("grant_type");
        if(prop != null){
            return  prop;
        }else{
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }

    public String getUserId22() {
        String prop = properties.getProperty("user_id");
        if(prop != null){
            return  prop;
        }else{
            throw new RuntimeException("property client_id is not specified in the config.properties file");
        }
    }
}
