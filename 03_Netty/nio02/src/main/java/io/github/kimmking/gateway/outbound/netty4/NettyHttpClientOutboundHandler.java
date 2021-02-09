package io.github.kimmking.gateway.outbound.netty4;

import io.github.kimmking.gateway.outbound.LoadBalance;
import io.github.kimmking.gateway.outbound.OutboundHander;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.util.CharsetUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okio.Utf8;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

@Slf4j
public class NettyHttpClientOutboundHandler extends ChannelInboundHandlerAdapter implements OutboundHander {

    private NettyHttpClient client;

    private List<String> backendUrls;

    private static int number = 0;

    public NettyHttpClientOutboundHandler(List<String> backendUrls) {
        this.backendUrls = backendUrls;
        client = new NettyHttpClient();
    }

    public NettyHttpClientOutboundHandler() {
    }

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        log.info("channelActive");
//        ctx.channel().writeAndFlush(fullRequest);
//    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            final String byteBuf = content.content().toString(CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(byteBuf.getBytes()));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", byteBuf.length());
            ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            ctx.flush();
            ctx.close();
        }
    }

    @SneakyThrows
    @Override
    public void handle(ChannelHandlerContext ctx, FullHttpRequest fullRequest) {
        // route
        // 可选的负载策略有：RANDOM、ROUND_ROBIN
        String route = route(backendUrls, LoadBalance.ROUND_ROBIN);
        final String url = route + fullRequest.uri();


        URI uri = new URI(url);
//        client.connect(uri.getHost(), uri.getPort());

        client.connect(ctx, fullRequest);

    }

    @Override
    public void filter(ChannelHandlerContext ctx, FullHttpRequest fullRequest) {
        fullRequest.headers().add("nio", "sakila");
    }

    @Override
    public String route(List<String> endpoints, LoadBalance loadBalance) {
        switch (loadBalance) {
            case RANDOM:
                number = new Random().nextInt(endpoints.size());
                break;
            case ROUND_ROBIN:
                if (++number > endpoints.size() - 1) {
                    number = 0;
                }
                break;
            default:
                break;
        }

        return endpoints.get(number);
    }
}