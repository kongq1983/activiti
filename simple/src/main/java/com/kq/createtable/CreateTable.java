package com.kq.createtable;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;

/**
 * CreateTable
 *
 * @author kq
 * @date 2019-04-09
 */
public class CreateTable {

    static String url = "jdbc:mysql://localhost:3306/activiti?characterEncoding=UTF-8&nullCatalogMeansCurrent=true&serverTimezone=GMT&useSSL=false";

    /**
     * 注意: nullCatalogMeansCurrent=true
     * @param args
     */
    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfigurationImpl.DB_SCHEMA_UPDATE_CREATE);
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();
    }

}
