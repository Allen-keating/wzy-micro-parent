package com.wzy.cloud.gateway.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理过滤器
 */
@Component
public class MyErrorFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE; // 关键：类型必须是 error
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println(">>> 进入自定义异常过滤器 MyErrorFilter");

        // 1. 获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();

        // 2. 获取抛出的异常信息
        ZuulException exception = (ZuulException) currentContext.get("throwable");

        // 3. 构建错误响应对象
        Result result = new Result(false, "执行失败: " + exception.getMessage());

        // 4. 将对象转为 JSON 字符串并输出
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(result);

            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(jsonString);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}