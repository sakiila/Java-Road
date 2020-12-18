package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.client.callable.OkHttpCallable;
import org.springframework.cglib.proxy.Enhancer;

public final class Rpcfx {

    // fastjson反序列化默认是开启了autoType，使用下面方法指定白名单配对
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    /**
     * 客户端调用
     *
     * @param serviceClass 类名
     * @param url          服务端地址
     * @param <T>
     * @return
     */
    public static <T> T create(final Class<T> serviceClass, final String url) {

        // 动态代理
        return (T) Enhancer.create(serviceClass, new RpcfxInterceptor(serviceClass, url, new OkHttpCallable()));

        // 字节码
    }

}
