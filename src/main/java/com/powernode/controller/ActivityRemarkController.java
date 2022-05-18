package com.powernode.controller;

import com.powernode.commns.Constant;
import com.powernode.commns.ResponseObject;
import com.powernode.pojo.ActivityRemark;
import com.powernode.pojo.User;
import com.powernode.service.ActivityRemarkService;
import com.powernode.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/activityRemark")
public class ActivityRemarkController {

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @PostMapping
    private ResponseObject addActivityRemark(ActivityRemark activityRemark, HttpSession session){
        ResponseObject ro = new ResponseObject();
        activityRemark.setId(UUID.randomUUID().toString().replace("-",""));
        activityRemark.setCreateBy(((User)session.getAttribute(Constant.LOGIN_USER)).getId());
        activityRemark.setCreateTime(DateUtil.date2String(new Date()));
        activityRemarkService.addActivityRemark(activityRemark);
        ro.setStateCode(200);
        return ro;
    }
}
