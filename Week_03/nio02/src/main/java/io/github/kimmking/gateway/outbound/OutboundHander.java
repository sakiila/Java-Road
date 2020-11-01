package io.github.kimmking.gateway.outbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public abstract class OutboundHander implements HttpRequestFilter, HttpEndpointRouter {

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        return;
    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        return;
    }

    @Override
    public String route(List<String> endpoints) {
        return route(endpoints, LoadBalance.RANDOM);
    }

    public String route(List<String> endpoints, LoadBalance loadBalance) {
        return "";
    }

}
