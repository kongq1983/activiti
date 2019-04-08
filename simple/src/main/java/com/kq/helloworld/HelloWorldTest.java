package com.kq.helloworld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * HelloWorldTest
 *
 * @author kq
 * @date 2019/4/8
 */
public class HelloWorldTest {

    public static void main(String[] args) {
        //创建流程引擎，使用内存数据库
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();

        //部署流程定义
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("helloworld.bpmn20.xml").deploy();

        //验证已部署 流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .singleResult();

        System.out.println("key="+processDefinition.getKey());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave");

        System.out.println("processInstance="+processInstance);

        System.out.println("pid="+processInstance.getId()+"  pdid="+processInstance.getProcessDefinitionId());

    }

}
