package com.ylsoft.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


// Zuul过滤器集成ZuulFilter
@Component
public class LoginFilter  extends ZuulFilter {
    /**
     * 过滤器类型前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器的执行顺序,越小越往后拍如果我再写个9的，代表先执行10再执行9的过滤器
     * @return
     */
    @Override
    public int filterOrder() {
        return 10;
    }

    /**
     * 过滤器是否生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // 获取zuul的上下文请求
        RequestContext context = RequestContext.getCurrentContext();
        // 获取请求头
        HttpServletRequest request = context.getRequest();
        String token = request.getParameter("token-user");
        // 判断请求里有没有token-user这个请求信息,token为空
        if(StringUtils.isBlank(token)){
            // 过滤该请求不路由
            context.setSendZuulResponse(false);

            // 设置响应状态码为401
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            // 设置响应信息
            context.setResponseBody("{\"status\":\"401\", \"text\":\"request error!\"}");
        }
        // 如果校验通过。把内容放到上下文中后继续往后执行
        context.set("token-user",token);
        return null;
    }
}
