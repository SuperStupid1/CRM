package com.powernode.controller;

import com.powernode.commns.Constant;
import com.powernode.commns.ResponseObject;
import com.powernode.pojo.Activity;
import com.powernode.pojo.User;
import com.powernode.service.ActivityService;
import com.powernode.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    /**
     * 查询所有市场活动
     *
     * @return
     */
    @GetMapping
    private ResponseObject findAll() {
        ResponseObject ro = new ResponseObject();
        ro.setStateCode(200);
        List<Activity> activities = activityService.findAll();
        ro.setData(activities);
        return ro;
    }

    /**
     * 添加市场活动
     *
     * @param activity
     * @param session
     * @return
     */
    @PostMapping
    private ResponseObject addActivity(Activity activity, HttpSession session) {
        // 给activity 的id create_by create_time 赋值
        activity.setId(UUID.randomUUID().toString().replace("-", ""));
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        activity.setCreateBy(user.getId());
        activity.setCreateTime(DateUtil.date2String(new Date()));
        activityService.addActivity(activity);
        ResponseObject ro = new ResponseObject();
        ro.setStateCode(200);
        return ro;
    }

    /**
     * 删除指定市场活动
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    private ResponseObject delActivity(@PathVariable("ids") String ids) {
        activityService.delActivity(ids);
        ResponseObject ro = new ResponseObject();
        ro.setStateCode(200);
        return ro;
    }

    /**
     * 根据id查询市场活动
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private ResponseObject findActivity(@PathVariable("id") String id) {
        ResponseObject ro = new ResponseObject();
        Activity activity = activityService.findActivity(id);
        ro.setData(activity);
        ro.setStateCode(200);
        return ro;
    }

    /**
     * 修改指定市场活动信息
     *
     * @param activity
     * @param session
     * @return
     */
    @PostMapping("update")
    private ResponseObject update(Activity activity, HttpSession session) {
        ResponseObject ro = new ResponseObject();
        // 设置修改人 和 修改时间
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        activity.setEditBy(user.getId());
        activity.setEditTime(DateUtil.date2String(new Date()));
        activityService.update(activity);

        ro.setStateCode(200);
        return ro;
    }

    /**
     * 多条件查询
     * @param activity
     * @return
     */
    @GetMapping("/conditionsFind")
    private ResponseObject conditionsFind(Activity activity) {
        ResponseObject ro = new ResponseObject();
        List<Activity> activityList = activityService.conditionsFind(activity);
        ro.setStateCode(200);
        ro.setData(activityList);
        return ro;
    }
}
