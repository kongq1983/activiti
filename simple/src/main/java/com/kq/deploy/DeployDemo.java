package com.kq.deploy;

import com.kq.config.Config;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;

import java.util.List;

/**
 * DeployDemo
 *
 * @author kq
 * @date 2019-04-11
 */
public class DeployDemo {

    public static void main(String[] args) {

        ProcessEngineConfiguration config = Config.getConfiguration();
        ProcessEngine processEngine = config.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<Deployment> list = repositoryService.createDeploymentQuery().deploymentTenantId("1").list();
//        Deployment d = repositoryService.createDeploymentQuery().deploymentTenantId("1").singleResult();
//        System.out.println(d);

        for(Deployment d : list) {
            System.out.println(d);
        }


    }

}
