package com.kq.activiti.user;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * UserTest
 *
 * @author kq
 * @date 2019-04-10
 */
public class UserTest {

    //使用默认的acitiviti.cfg.xml作为参数，当创建activitirule时，会自动创建引擎对象processEngine。

    @Rule
    public ActivitiRule ar = new ActivitiRule();

    IdentityService is ;

    private String username = "activititest";

    @Before
    public void before(){
        is = ar.getIdentityService();
//        User userInDb = is.createUserQuery().userId(username).singleResult();
//        if(userInDb!=null) {
//            is.deleteUser(username);
//        }

    }

    @Test
    public void modifyUser() {
        User userInDb = is.createUserQuery().userId(username).singleResult();

        if(userInDb!=null) {
            userInDb.setPassword("1234567");
            is.saveUser(userInDb);
        }

    }


    @Test
    public void testUser() throws Exception {
        IdentityService is = ar.getIdentityService();
        User user = is.newUser(username);
        user.setFirstName("king");
        user.setLastName("kong");
        user.setEmail(username+"@gamil.com");
        user.setPassword("1234567");
        is.saveUser(user);

//        User userInDb = is.createUserQuery().userId("admin").singleResult();
//        assertNotNull(userInDb);
//        is.deleteUser("admin");
//        userInDb = is.createUserQuery().userId("admin").singleResult();
//        assertNull(userInDb);

    }


}
