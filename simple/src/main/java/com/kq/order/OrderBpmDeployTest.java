package com.kq.order;

import com.kq.config.Config;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * OrderBpmDeployTest
 *
 * @author kq
 * @date 2022-04-24 19:36
 * @since 1.0.0
 */
public class OrderBpmDeployTest {

    public static void main(String[] args) {

        ProcessEngineConfiguration cfg =  Config.getConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/order.bpmn").deploy();
    }

}
