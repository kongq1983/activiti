package com.kq.order;

import com.kq.config.Config;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

/**
 * @author kq
 * @date 2022-04-24 14:13
 * @since 2020-0630
 */
public class OrderDeployTest {

    public static void main(String[] args) {

        ProcessEngineConfiguration cfg =  Config.getConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
//        repositoryService.createDeployment().addClasspathResource("order.bpmn20.xml").deploy();
//        repositoryService.createDeployment().addClasspathResource("order-condition.bpmn20.xml").deploy();
        repositoryService.createDeployment().addClasspathResource("order-condition-1.bpmn20.xml").deploy();
//        repositoryService.createDeployment().addClasspathResource("order.bpmn20.xml").deploy();



    }

}
