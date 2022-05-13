package com.powernode.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tbl_activity
 */
@Data
public class Activity implements Serializable {

    /**
     * 修改者用户
     */
    private User editUser;
    /**
     * 创建者用户
     */
    private User createUser;
    /**
     * 所有者用户
     */
    private User ownerUser;

    /**
     * 市场活动id
     */
    private String id;

    /**
     * 所有者id
     */
    private String owner;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String startDate;

    /**
     * 
     */
    private String endDate;

    /**
     * 
     */
    private String cost;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String createTime;

    /**
     * 
     */
    private String createBy;

    /**
     * 
     */
    private String editTime;

    /**
     * 
     */
    private String editBy;

    private static final long serialVersionUID = 1L;
}