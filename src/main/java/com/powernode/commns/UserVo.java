package com.powernode.commns;

import lombok.Data;

/**
 * 接收客户端传入信息的类
 */

@Data
public class UserVo {
    private String loginAct;
    private String loginPwd;
    private String ipAddress;
}
