package com.powernode.service;

import com.powernode.pojo.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 查询所有市场活动
     * @return
     */
    List<Activity> findAll();

    /**
     * 添加市场活动
     * @param activity
     */
    void addActivity(Activity activity);

    /**
     * 删除指定市场活动
     * @param ids
     */
    void delActivity(String ids);

    /**
     * 根据id查询市场活动
     * @param id
     * @return
     */
    Activity findActivity(String id);

    /**
     * 修改市场活动信息
     * @param activity
     */
    void update(Activity activity);

    /**
     * 多条件查询市场活动
     */
    List<Activity> conditionsFind(Activity activity);
}
