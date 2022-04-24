package com.kq.order;

import com.kq.config.Config;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kq
 * @date 2022-04-24 14:10
 * @since 2020-0630
 */
public class OrderStartProcessorTest {


    public static void main(String[] args) {

        ProcessEngineConfiguration cfg =  Config.getConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();

        IdentityService identityService = processEngine.getIdentityService();


        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 开始流程
        startProcess("order_process","order:1",runtimeService,identityService);

//        dealTask(processEngine);

    }

    private static void startProcess(String key,String bussinessId,RuntimeService runtimeService,IdentityService identityService) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", "101");
        //设置办理人、候选人、候选组
        map.put("loginUser", "king");
        map.put("assigneeUserId", "guest");
        map.put("candidateUsers", "test1,test2");
        map.put("candidateGroups", "group1,group2");
        ProcessInstance processInstance = null;
        try {
            // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
            identityService.setAuthenticatedUserId("admin");
            //  ACT_RU_EXECUTION ACT_RU_TASK
            processInstance = runtimeService.startProcessInstanceByKey(key, bussinessId, map);
            System.out.println("pid="+processInstance.getId()+"  pdid="+processInstance.getProcessDefinitionId());
            String processInstanceId = processInstance.getId();
            System.out.println(processInstanceId);
        } finally {
            identityService.setAuthenticatedUserId(null);
        }

    }



    private static void dealTask(ProcessEngine processEngine) {


        TaskService taskService = processEngine.getTaskService();
//        taskService.claim();

//        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();
//        Task submitTask = taskService.createTaskQuery().taskCandidateOrAssigned("1").singleResult();
//        List<Task> submitTasks = taskService.createTaskQuery().taskAssignee("admin").list();
        List<Task> submitTasks = taskService.createTaskQuery().taskCandidateOrAssigned("admin1").list();
//        Task submitTask = taskService.createTaskQuery().taskCandidateGroup("1").singleResult();
        for(Task task : submitTasks) {
            System.out.println(task.getId() + ":" + task.getTaskDefinitionKey());
        }
    }

}
