package com.atguigu.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //  ?age=22&username=zhangsan
        String username = request.getQueryParams().getFirst("username");
        if(StringUtils.isEmpty(username)){
            System.out.println("username = " + username);
            System.out.println("用户名称不能为空,拒绝访问");
            response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return response.setComplete(); //拒绝访问
        }

        Mono<Void> filter = chain.filter(exchange);
        System.out.println("请求回来时需要做的事情");
        return filter; //放行
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
