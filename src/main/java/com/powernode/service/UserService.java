package com.powernode.service;

import com.powernode.commns.UserVo;
import com.powernode.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 登录
     * @return
     */
    User login(UserVo userVo);

    /**
     * 查询所有用户
     */
    List<User> getAll();
}
