package com.kq.activiti.deploy;

import com.kq.OnboardingRequest;
import com.kq.activiti.BaseTest;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * DeployTest
 *
 * @author kq
 * @date 2019-04-11
 */
public class DeployTest extends BaseTest{

    private RepositoryService repositoryService;


    @Before
    public void before(){
        repositoryService = this.ar.getRepositoryService();

    }


    /**
     * classpath方式
     */
    @Test
    public void testDeployByAddClasspathResource(){
        Deployment deployment =
                repositoryService.createDeployment().name("请假流程").addClasspathResource("qingjia.bpmn20.xml").deploy();

        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }


    /**
     * inputStream方式
     */
    @Test
    public void testDeployByAddInputStream() throws  Exception{


        try(InputStream in = OnboardingRequest.class.getResourceAsStream("/qingjia.bpmn20.xml");){
            Deployment deployment =
                    repositoryService.createDeployment().name("请假流程1").addInputStream("qingjia.bpmn20.xml",in).deploy();
            System.out.println("部署ID："+deployment.getId());//1
            System.out.println("部署时间："+deployment.getDeploymentTime());
        }



    }



    @Test
    public void testDeployByzip(){
        InputStream in = OnboardingRequest.class.getResourceAsStream("/process.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = repositoryService//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }


}
