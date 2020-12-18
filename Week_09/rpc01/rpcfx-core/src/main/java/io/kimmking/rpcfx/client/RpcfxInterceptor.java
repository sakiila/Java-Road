package io.kimmking.rpcfx.client;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.client.callable.RpcfxCallable;
import io.kimmking.rpcfx.exception.RpcfxException;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class RpcfxInterceptor<T> implements MethodInterceptor {

    private Class<T> serviceClass;

    private String url;

    private RpcfxCallable callable;

    public RpcfxInterceptor(Class<T> serviceClass, String url, RpcfxCallable callable) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.callable = callable;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        RpcfxRequest<T> request = new RpcfxRequest<T>();
        request.setServiceClass(this.serviceClass);
        request.setMethod(method.getName());
        request.setParams(objects);
        RpcfxResponse response = callable.call(request, url);

        if (response.getStatus()) {
            return response.getResult();
        }

        RpcfxException exception = response.getException();
        if (exception != null) {
            throw exception;
        }

        throw new RpcfxException("unknow error");
    }
}
