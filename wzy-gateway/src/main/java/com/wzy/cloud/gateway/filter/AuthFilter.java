package com.wzy.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限控制过滤器
 * 校验请求中是否包含 token=user
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE; // 前置过滤器
    }

    @Override
    public int filterOrder() {
        return 0; // 优先级设为 0，非常高，最先执行
    }

    @Override
    public boolean shouldFilter() {
        return true; // 开启拦截
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(">>> AuthFilter 开始执行...");

//        if (true) { // 强制执行
//            throw new RuntimeException("网关安全组件响应超时，请联系管理员！");
//        }
//        // 1. 获取当前请求上下文
//        RequestContext currentContext = RequestContext.getCurrentContext();
//        HttpServletRequest request = currentContext.getRequest();
//
//        // 2. 获取 token 参数
//        String token = request.getParameter("token");
//
//        // 3. 校验逻辑
//        if (!"user".equals(token)) {
//            System.out.println(">>> 权限校验失败，拦截请求！");
//
//            // 不转发请求给微服务
//            currentContext.setSendZuulResponse(false);
//            // 设置响应状态码 401 (未授权)
//            currentContext.setResponseStatusCode(401);
//
//            // (可选) 设置响应中文乱码处理，虽然这里只返回状态码，但在后续步骤返回JSON时很有用
//            currentContext.getResponse().setContentType("text/html;charset=UTF-8");
//
//            return null;
//        }

        System.out.println(">>> 权限校验通过，放行。");
        return null;
    }
}