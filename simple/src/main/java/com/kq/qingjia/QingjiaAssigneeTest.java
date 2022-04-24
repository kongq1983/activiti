package com.kq.qingjia;

import com.kq.config.Config;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 观察act_ru_identitylink是否有多1条数据
 * act_ru_task的Assignee是否设置值
 * @author kq
 * @date 2022-02-21 16:45
 * @since 2020-0630
 */
public class QingjiaAssigneeTest {


    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
//                .buildProcessEngine();

//        String url = "jdbc:mysql://localhost:3306/activiti?characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";

        String url = Config.url;

        List<String> list = new ArrayList<>();
//        list.add("start");
        list.add("loadTask");
        list.add("executeTask");


        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver");
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
//        repositoryService.createDeployment().addClasspathResource("qingjia.bpmn20.xml").deploy();
        // SELECT * FROM act_re_procdef
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        // 取最新1个版本
//        ProcessDefinition processDefinition = repositoryService.getProcessDefinition("qingjia:8:47504");
        System.out.println("key="+processDefinition.getKey());

        Map<String, Object> variables = new HashMap<String, Object>();

        if(list.contains("start")) {

            RuntimeService runtimeService = processEngine.getRuntimeService();

            variables.put("applyUser", "king"); // ACT_RU_VARIABLE
            variables.put("days", 3);

            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia", variables);

            System.out.println("pid=" + processInstance.getId() + "  pdid=" + processInstance.getProcessDefinitionId());
        }

        TaskService taskService = processEngine.getTaskService();
//        Task taskOfLeader = taskService.createTaskQuery().taskCandidateGroup("deptLeader").singleResult();

        // loadTask

        List<Task> list1 = null;
        if(list.contains("loadTask") || list.contains("executeTask")) {
            //  select distinct RES.* from ACT_RU_TASK RES inner join ACT_RU_IDENTITYLINK I on I.TASK_ID_ = RES.ID_ WHERE RES.ASSIGNEE_ is null
            //  and I.TYPE_ = 'candidate' and ( I.GROUP_ID_ IN ( ? ) ) order by RES.ID_ asc LIMIT ? OFFSET ?
            // 参数:   deptLeader(String), 2147483647(Integer), 0(Integer)

//            list1 = taskService.createTaskQuery().taskCandidateUser("admin").list();

//            list1 = taskService.createTaskQuery().taskAssignee("admin").listPage(0, 100);  // 待办事项
            list1 = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();

            System.out.println("任务列表list1="+list1);

        }

        if(list.contains("executeTask")) {

            // 比如先拿到任务列表 -> 指定处理人 -> 完成

            // 组 Group
//            List<Task> list1 = taskService.createTaskQuery().taskCandidateGroup("deptLeader").list();

            //查询代办人关联的任务，并且完成它  UserID
//                List<Task> list1 = taskService.createTaskQuery().taskAssignee("admin").listPage(0, 100);

            for (Task taskOfLeader : list1) {

                // 1. 设置act_ru_task的OWNER_
                // 2. act_ru_identitylink会加1加1条记录('participant','admin')  type=participant userId=admin
//                taskService.setOwner(taskOfLeader.getId(),"admin");
                System.out.println("setOwner------------------------------");

                // 认领任务  指定处理人   act_ru_task 的 _Assignee字段
                taskService.claim(taskOfLeader.getId(), "admin");

                // 取消处理人
                System.out.println(taskOfLeader.getId()+",取消处理人");
//                taskService.unclaim(taskOfLeader.getId());



                variables = new HashMap<String, Object>();
                variables.put("approved", true);
                // 审批
//                taskService.complete(taskOfLeader.getId(), variables);

                System.out.println("id=" + taskOfLeader.getId() + ",Assignee=" + taskOfLeader.getAssignee() + ",ProcessDefinitionId=" + taskOfLeader.getProcessDefinitionId());

            }
        }


        HistoryService historyService = processEngine.getHistoryService();
        long count = historyService.createHistoricProcessInstanceQuery().finished().count();

        System.out.println("count="+count);


    }


}
