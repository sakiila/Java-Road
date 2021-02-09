package io.github.kimmking.gateway.outbound;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.github.kimmking.gateway.router.HttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;

public interface OutboundHander extends HttpRequestFilter, HttpEndpointRouter {

    default void handle(final ChannelHandlerContext ctx, final FullHttpRequest fullRequest) {
        return;
    }

    @Override
    default void filter(ChannelHandlerContext ctx, FullHttpRequest fullRequest) {
        return;
    }

    @Override
    default String route(List<String> endpoints) {
        return route(endpoints, LoadBalance.RANDOM);
    }

    default String route(List<String> endpoints, LoadBalance loadBalance) {
        return "";
    }

}
