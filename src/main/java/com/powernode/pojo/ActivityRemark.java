package com.powernode.pojo;

import lombok.Data;

/**
 * 评论备注的实体类
 */
@Data
public class ActivityRemark {
    private String id;

    private String noteContent;

    private String createTime;

    private String createBy;

    private String editTime;

    private String editBy;

    private String editFlag;

    private String activityId;
}