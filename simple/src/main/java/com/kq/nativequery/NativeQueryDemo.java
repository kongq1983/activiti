package com.kq.nativequery;

import com.kq.config.Config;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.util.List;

/**
 * NativeQueryDemo
 *
 * @author kq
 * @date 2019-04-12
 */
public class NativeQueryDemo {

    public static void main(String[] args) {

        ProcessEngineConfiguration cfg = Config.getConfiguration();

        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        List<Deployment> list = repositoryService.createNativeDeploymentQuery()
                .sql("select * from act_re_deployment where key_=#{key}")
                    .parameter("key","qingjia").list();

        for(Deployment d : list) {
            System.out.println("id="+d.getId()+" name="+d.getName());
        }

        long count = repositoryService.createNativeDeploymentQuery()
                .sql("select count(*) from act_re_deployment where key_=#{key}")
                    .parameter("key","qingjia").count();

        System.out.println("count="+count);


    }

}
