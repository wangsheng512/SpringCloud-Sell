package com.wsheng.apigeteway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.wsheng.apigeteway.exception.RateLimiterException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support
        .FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support
        .FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 限流
 * @Auther: wsheng
 * @Date: 2018/11/6 15:14
 * @Description:
 */
@Component
public class RateLimiterFilter extends ZuulFilter{

    //令牌桶算法的实现
    private final static RateLimiter RATE_LIMITER = RateLimiter.create(100);

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER-1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        if(!RATE_LIMITER.tryAcquire()){
            throw new RateLimiterException();
        }
        return null;
    }
}
