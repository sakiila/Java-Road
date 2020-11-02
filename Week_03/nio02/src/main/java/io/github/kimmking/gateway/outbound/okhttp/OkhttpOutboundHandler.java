package io.github.kimmking.gateway.outbound.okhttp;

import io.github.kimmking.gateway.outbound.LoadBalance;
import io.github.kimmking.gateway.outbound.OutboundHander;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler extends OutboundHander {

    private static Logger logger = LoggerFactory.getLogger(OkhttpOutboundHandler.class);

    private OkHttpClient client;

    private List<String> backendUrls;

    public static int number = 0;

    public OkhttpOutboundHandler(List<String> backendUrls) {
        this.backendUrls = backendUrls;

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        // route
        // 可选的负载策略有：RANDOM、ROUND_ROBIN
        String route = route(backendUrls, LoadBalance.ROUND_ROBIN);
        final String url = route + fullRequest.uri();
        logger.info("url: {}", url);

        // 转换Header类型
        HttpHeaders headers = fullRequest.headers();
        Map<String, String> collect = headers.entries().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Headers newHeaders = Headers.of(collect).newBuilder().build();

        FullHttpResponse result = null;

        Request request = new Request.Builder().url(url).headers(newHeaders).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                // ... handle failed request
                throw new IOException();
            }

            String responseBody = response.body().string();
            responseBody = responseBody + "\nServer Url: " + request.url();
//            logger.info("responseBody: {}", responseBody);

            // ... do something with response
            byte[] body = responseBody.getBytes();

            result = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
            result.headers().set("Content-Type", "application/json");
            result.headers().setInt("Content-Length", body.length);

        } catch (IOException e) {
            // ... handle IO exception
        } finally {
            ctx.write(result);
            ctx.flush();
        }

    }

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
//        System.out.println("============== start filter ==============");
//        System.out.println("============== before filter ==============");
//        System.out.println("fullRequest.headers() = " + fullRequest.headers());
//        System.out.println("fullRequest.uri() = " + fullRequest.uri());
//        System.out.println("fullRequest.content() = " + fullRequest.content());
//        System.out.println("fullRequest.method() = " + fullRequest.method());

        fullRequest.headers().add("user-name", "sakila");
        fullRequest.headers().add("user-sex", "male");
        fullRequest.headers().add("user-age", "23");

//        System.out.println("============== after filter ==============");
//        System.out.println("fullRequest.headers() = " + fullRequest.headers());
//        System.out.println("fullRequest.uri() = " + fullRequest.uri());
//        System.out.println("fullRequest.content() = " + fullRequest.content());
//        System.out.println("fullRequest.method() = " + fullRequest.method());
//        System.out.println("============== end filter ==============");

        return;
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
