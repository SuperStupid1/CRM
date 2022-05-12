package com.powernode.mapper;

import com.powernode.commns.UserVo;
import com.powernode.pojo.User;

import java.util.List;

/**
 * @Entity com.powernode.pojo.User
 */
public interface UserMapper {

    /**
     * 根据用户名查询用户
     * @return 用户
     */
    User selectByLoginAct(UserVo userVo);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectById(String id);

    /**
     * 查询所有用户
     */
    List<User> selectAll();
}




