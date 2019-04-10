package com.kq.activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * BaseTest
 *
 * @author kq
 * @date 2019-04-10
 */
public class BaseTest {

    @Rule
    public ActivitiRule ar = new ActivitiRule();

    IdentityService is;

    @Before
    public void before(){
        is = ar.getIdentityService();
    }


}
