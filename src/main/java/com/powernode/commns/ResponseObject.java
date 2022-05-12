package com.powernode.commns;

import lombok.Data;

/**
 * 响应结果的类
 */
@Data
public class ResponseObject {
    /**
     * 跳转的页面
     */
    private String viewName;

    /**
     * 状态码 ,200代表成功，500代表失败
     */
    private int stateCode;

    /**
     * 响应的错误信息
     */
    private String msg;

    /**
     * 响应的数据对象
     */
    private Object data;
}
