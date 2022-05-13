package com.powernode.service;

import com.github.pagehelper.PageInfo;
import com.powernode.pojo.Activity;

import java.util.List;

public interface ActivityService {

    /**
     * 查询所有市场活动
     * @return
     */
    PageInfo<Activity> findAll(Integer pageNum,Integer pageSize);

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
    PageInfo<Activity> conditionsFind(Activity activity,Integer pageNum,Integer pageSize);
}
