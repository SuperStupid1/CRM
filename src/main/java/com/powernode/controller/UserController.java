package com.powernode.controller;

import com.powernode.commns.Constant;
import com.powernode.commns.ResponseObject;
import com.powernode.commns.UserVo;
import com.powernode.exception.CRMException;
import com.powernode.pojo.User;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录的方法
     *
     * @param vo      保存前端传输的用户信息的 UserVo对象
     * @param req     请求对象
     * @param resp    响应对象
     * @param checked 获取前端记住密码选项是否勾选
     * @return
     */
    @RequestMapping("/login")
    private ResponseObject login(UserVo vo, HttpServletRequest req, HttpServletResponse resp, String checked) {
        // 获取客户端的请求ip地址
        String addr = req.getRemoteAddr();
        vo.setIpAddress(addr);
        ResponseObject ro = new ResponseObject();

        User user = userService.login(vo);
        ro.setStateCode(200);
        // 设置登录成功的跳转页面
        ro.setViewName("/CRM/workbench/index.html");

        // 将用户信息保存到session
        HttpSession session = req.getSession();
        session.setAttribute(Constant.LOGIN_USER, user);

        Cookie cookie1;
        Cookie cookie2;
        // 判断用户是否勾选10天免登陆
        if ("true".equals(checked)) {
            // 将用户名添加进cookie
            cookie1 = new Cookie("loginAct", user.getLoginAct());
            cookie2 = new Cookie("loginPwd", user.getLoginPwd());
            // 设置保存时间
            cookie1.setMaxAge(60 * 60 * 24 * 10);
            cookie2.setMaxAge(60 * 60 * 24 * 10);
            // 设置cookie作用范围为登录页面（默认是当前项目）
            String contextPath = req.getContextPath();
            cookie1.setPath(contextPath + "/settings/qx/user/login.html");
            cookie2.setPath(contextPath + "/settings/qx/user/login.html");

        } else {
            // 如果用户取消勾选了10天免登陆将cookie进行覆盖，并将存活时间设置成0
            cookie1 = new Cookie("loginAct", null);
            cookie2 = new Cookie("loginPwd", null);
            cookie1.setMaxAge(0);
            cookie2.setMaxAge(0);
            // 设置cookie作用范围为登录页面（默认是当前项目）
            String contextPath = req.getContextPath();
            cookie1.setPath(contextPath + "/settings/qx/user/login.html");
            cookie2.setPath(contextPath + "/settings/qx/user/login.html");
        }
        // 保存cookie
        resp.addCookie(cookie1);
        resp.addCookie(cookie2);

        return ro;
    }

    /**
     * 获取登录用户信息
     *
     * @param session HttpSession对象
     * @return
     */
    @GetMapping("/getLoginUser")
    private ResponseObject getLoginUser(HttpSession session) {
        ResponseObject ro = new ResponseObject();
        User user = (User) session.getAttribute(Constant.LOGIN_USER);
        ro.setData(user);
        ro.setStateCode(200);
        return ro;
    }


    @GetMapping("/logout")
    private ResponseObject logout(HttpSession session){
        ResponseObject ro = new ResponseObject();
        session.removeAttribute(Constant.LOGIN_USER);
        ro.setStateCode(200);
        ro.setViewName("/CRM/settings/qx/user/login.html");
        return ro;
    }

    @GetMapping("/getAll")
    private ResponseObject getAll(){
        ResponseObject ro = new ResponseObject();
        ro.setStateCode(200);
        ro.setData(userService.getAll());
        return ro;
    }


}
