package com.kq.activiti;

import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * UserGroupTest
 *
 * @author kq
 * @date 2019-04-10
 */
public class UserGroupTest extends BaseTest{

    private String userId = "mytest";
    private String groupId = "testGroup";


    @Before
    public void before(){
        super.before();
        clear();
    }


    private void clear(){
        is.deleteUser(userId);
        is.deleteGroup(groupId);
        is.deleteMembership(userId,groupId);
    }

    @After
    public void after(){
        clear();
    }


    @Test
    public void userGroupTest(){

        //创建用户
        User user = is.newUser(userId);
        user.setEmail(userId+"@gamil.com");
        user.setPassword("1234567");
        is.saveUser(user);
        User loadUser = is.createUserQuery().userId(userId).singleResult();
        assertNotNull(loadUser);

        //创建组
        Group group = is.newGroup(groupId);
        group .setName("hr");
        group .setType("assignment");
        is.saveGroup(group);

        Group loadGroup = is.createGroupQuery().groupId(group.getId()).singleResult();
        assertNotNull(loadGroup);


        //创建用户和组关联
        is.createMembership(userId,groupId);

        User queryUser = is.createUserQuery().memberOfGroup(groupId).singleResult();
        assertEquals(queryUser.getId(),userId);

        Group queryGroup = is.createGroupQuery().groupMember(userId).singleResult();
        assertEquals(queryGroup.getId(),groupId);

    }

}
