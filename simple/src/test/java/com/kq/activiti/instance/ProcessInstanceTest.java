package com.kq.activiti.instance;

import com.kq.activiti.BaseTest;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * ProcessInstanceTest
 *
 * @author kq
 * @date 2019-04-11
 */
public class ProcessInstanceTest extends BaseTest{


    @Test
    public void testStart(){
        RepositoryService rs = this.ar.getRepositoryService();
        List<Deployment> list = rs.createDeploymentQuery().deploymentTenantId("1").list();

//        Optional<Deployment> d=  list.stream().max((o1, o2)-> o1.getId().compareTo(o2.getId()));
        Optional<Deployment> d=  list.stream().max(Comparator.comparing(Deployment::getId));
        ProcessEngine processEngine = ar.getProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("qingjia");

        System.out.println("processInstance id="+processInstance.getId());

        System.out.println(d.get().getId());

    }

}
