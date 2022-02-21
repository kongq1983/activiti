package com.kq.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * Config
 *
 * @author kq
 * @date 2019-04-11
 */
public class Config {

    public static String url = "jdbc:mysql://localhost:3306/activiti1?characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=GMT&useSSL=false";

    public static ProcessEngineConfiguration getConfiguration(){

        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver");
//                .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);

        return cfg;
    }

}
