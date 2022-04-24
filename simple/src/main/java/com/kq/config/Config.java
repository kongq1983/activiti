package com.kq.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.apache.commons.lang3.StringUtils;

/**
 * Config
 *
 * @author kq
 * @date 2019-04-11
 */
public class Config {

    public static String url = "jdbc:mysql://localhost:3306/activiti1?characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=GMT&useSSL=false";
    public static String url_1 = "jdbc:mysql://mysql.kq.com:23306/activiti?characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=GMT&useSSL=false";

    public static ProcessEngineConfiguration getConfiguration(){

        String place = System.getenv("place");
        System.out.println("place="+place);
        // -Dprofile=home
        if(StringUtils.equals(place,"home")) {
            ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                    .setJdbcUrl(url_1)
                    .setJdbcUsername("admin")
                    .setJdbcPassword("admin123")
                    .setJdbcDriver("com.mysql.cj.jdbc.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);
        ;
            return cfg;
        }

        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver");
//                .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);

        return cfg;
    }

    public static void main(String[] args) {
        String place = System.getenv("place");
        System.out.println("place="+place);
    }

}
