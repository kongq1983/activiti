package com.kq.common;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

/**
 * @author kq
 * @date 2022-04-24 17:04
 * @since 2020-0630
 */
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("MyTaskListener:"+delegateTask.getEventName());
        if ("assignment".equals(delegateTask.getEventName())) {
            System.out.println("代理人" + delegateTask.getAssignee());
            System.out.println("参与者数量" + delegateTask.getCandidates().size());
        }
        if ("create".equals(delegateTask.getEventName())) {
            System.out.println("代理人" + delegateTask.getAssignee());
            for (IdentityLink identityLink : delegateTask.getCandidates()) {
                if ("candidate".equals(identityLink.getType())) {
                    if (null != identityLink.getUserId()) {
                        System.out.println("参与者" + identityLink.getUserId());
                    } else if (null != identityLink.getGroupId()) {
                        System.out.println("参与组" + identityLink.getGroupId());
                    }
                }
            }
        }
    }
}