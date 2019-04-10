package com.kq.activiti;


import org.activiti.engine.identity.Group;
import org.junit.Test;

import java.util.List;

/**
 * GroupTest
 *
 * @author kq
 * @date 2019-04-10
 */
public class GroupTest extends BaseTest{


    @Test
    public void testInsertGroup(){
        Group group = is.newGroup("hrGroup");
        group .setName("hr");
        group .setType("assignment");
        is.saveGroup(group);

        List<Group> groupList = is.createGroupQuery().groupId("hrGroup").list();

        System.out.println(groupList);


    }

    @Test
    public void testDelete(){
        is.deleteGroup("hrGroup");
    }

}
