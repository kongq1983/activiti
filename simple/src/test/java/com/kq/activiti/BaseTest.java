package com.kq.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * BaseTest
 * 表结构: https://lucaslz.gitbooks.io/activiti-5-22/content/actru_identitylink_ff08_yun_xing_shi_liu_cheng_ren.html
 * @author kq
 * @date 2019-04-10
 */
public class BaseTest {

    protected String userId = "mytest";
    protected String groupId = "testGroup";

    @Rule
    public ActivitiRule ar = new ActivitiRule();

    protected IdentityService is;

    @Before
    public void before(){
        is = ar.getIdentityService();
    }

    protected void clear(){
        is.deleteUser(userId);
        is.deleteGroup(groupId);
        is.deleteMembership(userId,groupId);
    }






}
