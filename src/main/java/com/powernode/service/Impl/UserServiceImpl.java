package com.powernode.service.Impl;

import com.powernode.commns.UserVo;
import com.powernode.exception.CRMException;
import com.powernode.mapper.UserMapper;
import com.powernode.pojo.User;
import com.powernode.service.UserService;
import com.powernode.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserVo userVo) {
        // 获取查询结果
        User user = userMapper.selectByLoginAct(userVo);

        // 判断用户名是否存在
        if (user == null){
            throw new CRMException("用户名不存在");
        }
        // 判断密码是否正确
        if (!userVo.getLoginPwd().equals(user.getLoginPwd())){
            throw new CRMException("密码不正确");
        }
        // 判断是否在有效期内
        String now = DateUtil.date2String(new Date());
        if (now.compareTo(user.getExpireTime())>0){
            throw new CRMException("账户已过期");
        }
        // 判断用户是否被锁定
        if (user.getLockState().equals("0")){
            throw new CRMException("用户已被锁定");
        }
        // 判断用户的地址是否合法
        if (!user.getAllowIps().contains(userVo.getIpAddress())){
            throw new CRMException("ip受限");
        }
        return user;
    }

    /**
     * 查询所有用户
     * @return
     */
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }
}
