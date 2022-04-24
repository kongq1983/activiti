package com.kq.order;

import com.kq.config.Config;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kq
 * @date 2022-04-24 14:10
 * @since 2020-0630
 */
public class OrderTest {


    public static void main(String[] args) {

        ProcessEngineConfiguration cfg =  Config.getConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();

        Map<String,Object> variables = new HashMap();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //  ACT_RU_EXECUTION ACT_RU_TASK
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_1",variables);
        System.out.println("pid="+processInstance.getId()+"  pdid="+processInstance.getProcessDefinitionId());


    }


    private static void dealTask(ProcessEngine processEngine) {

        TaskService taskService = processEngine.getTaskService();
//        taskService.claim();

//        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
        Task taskOfLeader = taskService.createTaskQuery().task
    }

}
