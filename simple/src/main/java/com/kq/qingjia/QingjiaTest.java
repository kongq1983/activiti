package com.kq.qingjia;

import com.kq.config.Config;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * 这个是全新的一套
 * QingjiaTest
 *
 * @author kq
 * @date 2019/4/8
 */
public class QingjiaTest {

    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
//                .buildProcessEngine();

//        String url = "jdbc:mysql://localhost:3306/activiti?characterEncoding=UTF-8&serverTimezone=GMT&useSSL=false";

        String url = Config.url;


        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl(url)
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver");
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("qingjia.bpmn20.xml").deploy();

//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().singleResult();
        // 取最新1个版本
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().latestVersion().singleResult();
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
