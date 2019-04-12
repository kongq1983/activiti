package com.kq.activiti.financial;

import com.kq.activiti.BaseTest;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import static org.junit.Assert.*;




/**
 * FinancialReportProcessTest
 *
 * @author kq
 * @date 2019-04-12
 */
public class FinancialReportProcessTest extends BaseTest{


    @Test
    public void testDeploy(){
        RepositoryService repositoryService = this.ar.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().name("FinancialReportProcess").tenantId("1")
                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
                .deploy();

        assertNotNull(deployment);

        System.out.println("id="+deployment.getId());

    }

    @Test
    public void testStart(){

        RuntimeService runtimeService = this.ar.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");

        System.out.println("processInstance="+processInstance);
    }


}
