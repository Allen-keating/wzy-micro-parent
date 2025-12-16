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

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletResponse response = currentContext.getResponse();

        // 【修改点1】使用 Throwable 接收，避免 ClassCastException
        Throwable throwable = currentContext.getThrowable();

        // 【修改点2】安全地获取异常信息 (如果是包装异常，可能需要获取 cause)
        String message = "执行失败";
        if (throwable != null) {
            // 如果是 ZuulException 且有 Cause，通常 Cause 里的 message 才是原始报错
            if (throwable.getCause() != null) {
                message += ": " + throwable.getCause().getMessage();
            } else {
                message += ": " + throwable.getMessage();
            }
        }

        // 3. 构建错误响应对象
        Result result = new Result(false, message);

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