package com.wzy.cloud.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 第一个 Zuul 过滤器
 */
@Component // 交给 Spring 管理
public class MyFilter1 extends ZuulFilter {

    // 过滤器类型：PRE (请求路由前执行)
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    // 执行顺序：数值越小，优先级越高
    @Override
    public int filterOrder() {
        return 1;
    }

    // 是否生效
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 过滤器的具体逻辑
    @Override
    public Object run() throws ZuulException {
        System.out.println(">>> 执行 MyFilter1 过滤器");
        return null;
    }
}