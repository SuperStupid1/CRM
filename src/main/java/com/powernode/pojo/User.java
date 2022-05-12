package com.powernode.pojo;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName tbl_user
 */
@Data
public class User implements Serializable {
    /**
     * uuid
            
     */
    private String id;

    /**
     * 
     */
    private String loginAct;

    /**
     * 
     */
    private String name;

    /**
     * 密码不能采用明文存储，采用密文，MD5加密之后的数据
     */
    private String loginPwd;

    /**
     * 
     */
    private String email;

    /**
     * 失效时间为空的时候表示永不失效，失效时间为2018-10-10 10:10:10，则表示在该时间之前该账户可用。
     */
    private String expireTime;

    /**
     * 锁定状态为空时表示启用，为0时表示锁定，为1时表示启用。
     */
    private String lockState;

    /**
     * 
     */
    private String deptno;

    /**
     * 允许访问的IP为空时表示IP地址永不受限，允许访问的IP可以是一个，也可以是多个，当多个IP地址的时候，采用半角逗号分隔。允许IP是192.168.100.2，表示该用户只能在IP地址为192.168.100.2的机器上使用。
     */
    private String allowIps;

    /**
     * 
     */
    private String createtime;

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