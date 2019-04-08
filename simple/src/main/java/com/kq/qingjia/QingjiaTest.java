package com.kq.qingjia;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * QingjiaTest
 *
 * @author kq
 * @date 2019/4/8
 */
public class QingjiaTest {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
                .buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("qingjia.bpmn20.xml").deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        System.out.println("key="+processDefinition.getKey());

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> variables = new HashMap<String, Object>();
        variables.put("applyUser","king");
        variables.put("days",3);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia",variables);

        System.out.println("pid="+processInstance.getId()+"  pdid="+processInstance.getProcessDefinitionId());

        TaskService taskService = processEngine.getTaskService();
        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();

        taskService.claim(taskOfLeader.getId(),"admin");

        variables = new HashMap<String, Object>();
        variables.put("approved",true);
        taskService.complete(taskOfLeader.getId(),variables);

        taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();

        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished().count();

        System.out.println("count="+count);

    }

}
