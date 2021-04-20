package com.rma.gateway.filter;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * 局部过滤器
 * @author glf
 */
@Component
public class UserCheckGateWayFilter  extends AbstractGatewayFilterFactory  {
    private Logger log = LoggerFactory.getLogger(UserCheckGateWayFilter.class);
    private static final String TOKEN = "token";
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String url = exchange.getRequest().getPath().pathWithinApplication().value();
            log.info("请求URL:" + url);
            log.info("method:" + exchange.getRequest().getMethod());
            // GET，直接向下执行
            if (StrUtil.equalsIgnoreCase(request.getMethodValue(), HttpMethod.GET.name())
                    || StrUtil.containsIgnoreCase(request.getURI().getPath(), TOKEN)
                    || true) {
                return chain.filter(exchange);
            }

            log.warn("演示环境不能操作-> {},{}", request.getMethodValue(), request.getURI().getPath());
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.LOCKED);
            return response.setComplete();
        };
    }
}
