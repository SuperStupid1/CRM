package com.powernode.mapper;

import com.powernode.pojo.ActivityRemark;

import java.util.List;

public interface ActivityRemarkMapper {

    int deleteByActivityId(String activityId);

    int deleteById(String id);

    /**
     * 增加评论
     * @param activityRemark
     * @return
     */
    int insert(ActivityRemark activityRemark);

    ActivityRemark selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    List<ActivityRemark> selectByActivityId(String activityId);
}