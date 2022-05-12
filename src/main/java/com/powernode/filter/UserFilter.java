package com.powernode.filter;

import com.powernode.commns.Constant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录过滤器
 */
@WebFilter("/*")
public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        // 判断用户是否登录
        if (req.getSession().getAttribute(Constant.LOGIN_USER) == null) {
            String uri = req.getRequestURI();
            // 登录页面和登录的方法和静态资源放行
            if (uri.equals("/CRM/index.html") || uri.equals("/CRM/settings/qx/user/login.html") || uri.equals("/CRM/user/login")
                    || uri.endsWith(".js") || uri.endsWith(".css") || uri.endsWith("PNG") || uri.endsWith("png") || uri.endsWith("jpg")
                    || uri.endsWith("JPG")) {
                filterChain.doFilter(req,resp);
            }else {
                resp.sendRedirect("/CRM/settings/qx/user/login.html");
            }
        }else {
            // 如果登录了 直接放行
            filterChain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}
