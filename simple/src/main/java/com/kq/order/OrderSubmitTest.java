package com.kq.order;

import com.kq.config.Config;
import com.kq.util.UserUtil;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * OrderPayTest
 *
 * @author kq
 * @date 2022-04-24 20:18
 * @since 1.0.0
 */
public class OrderSubmitTest {

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



        List<Task> submitTasks = taskService.createTaskQuery().processInstanceId("20001").taskCandidateOrAssigned("king").list();
//        Task submitTask = taskService.createTaskQuery().taskCandidateGroup("1").singleResult();
        for(Task task : submitTasks) {
            System.out.println(task.getId() + ":" + task.getTaskDefinitionKey());

            taskService.complete(task.getId(), UserUtil.getUerMap("king","pay"));

        }
    }

}

