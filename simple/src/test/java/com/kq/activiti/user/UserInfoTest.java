package com.kq.activiti.user;

import com.kq.activiti.BaseTest;
import org.activiti.engine.identity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * UserInfoTest
 *
 * @author kq
 * @date 2019-04-10
 */
public class UserInfoTest extends BaseTest {

    public void before(){
        super.before();
        clear();
    }



    @Test
    public void userInfoTest(){

        User user = is.newUser(userId);
        user.setEmail(userId+"@gamil.com");
        user.setPassword("1234567");
        is.saveUser(user);
        User loadUser = is.createUserQuery().userId(userId).singleResult();
        assertNotNull(loadUser);

        is.setUserInfo(userId,"age","18");
        is.setUserInfo(userId,"phone","168xxxx2222");

        String age = is.getUserInfo(userId,"age");
        String phone = is.getUserInfo(userId,"phone");

        assertEquals("18",age);
        assertEquals("168xxxx2222",phone);

    }


}
