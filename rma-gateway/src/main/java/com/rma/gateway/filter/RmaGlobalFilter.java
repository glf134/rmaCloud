package com.rma.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class RmaGlobalFilter implements GlobalFilter, Ordered{
    private Logger log = LoggerFactory.getLogger(RmaGlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("网关全局过滤器-前置");

        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {
            log.info("网关全局过滤器-后置");
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
