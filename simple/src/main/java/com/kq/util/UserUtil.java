package com.kq.util;

import java.util.HashMap;
import java.util.Map;

/**
 * UserUtil
 *
 * @author kq
 * @date 2022-04-24 20:37
 * @since 1.0.0
 */
public class UserUtil {

    public static Map<String,Object> getUerMap(){
        return getUerMap("king");
    }

    public static Map<String,Object> getUerMap(String loginUser){
        Map<String, Object> map = new HashMap<>();
        map.put("id", "101");
        //设置办理人、候选人、候选组
        map.put("loginUser", loginUser);
        map.put("assigneeUserId", "guest");
        map.put("candidateUsers", "test1,test2");
        map.put("candidateGroups", "group1,group2");

        return map;
    }

}
