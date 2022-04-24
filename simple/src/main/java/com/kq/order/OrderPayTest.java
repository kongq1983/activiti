package com.kq.order;

import com.kq.config.Config;
import com.kq.util.UserUtil;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * OrderPayTest
 *
 * @author kq
 * @date 2022-04-24 20:18
 * @since 1.0.0
 */
public class OrderPayTest {

    public static void main(String[] args) {

        ProcessEngineConfiguration cfg =  Config.getConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();

        IdentityService identityService = processEngine.getIdentityService();


        RuntimeService runtimeService = processEngine.getRuntimeService();

        dealTask(processEngine);

    }


    private static void dealTask(ProcessEngine processEngine) {


        TaskService taskService = processEngine.getTaskService();
//        taskService.claim();

//        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        Task submitTask = taskService.createTaskQuery().taskCandidateOrAssigned("1").singleResult();
//        List<Task> submitTasks = taskService.createTaskQuery().taskAssignee("admin").list();

        // 支付或取消
        List<Task> submitTasks =  taskService.createTaskQuery().processInstanceId("32501").taskCandidateOrAssigned("king").list();
        System.out.println("loadTask "+submitTasks);
//        Task submitTask = taskService.createTaskQuery().taskCandidateGroup("1").singleResult();


        for(Task task : submitTasks) {
            System.out.println(task.getId() + ":" + task.getTaskDefinitionKey());

            if(task.getName().equals("支付")) {
                taskService.complete(task.getId(),  UserUtil.getUerMap("king","pay"));
                break;
            }

        }




    }

}

