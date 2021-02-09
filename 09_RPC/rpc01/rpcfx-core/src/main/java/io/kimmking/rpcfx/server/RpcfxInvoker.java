package io.kimmking.rpcfx.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {

    private RpcfxResolver resolver;

    public RpcfxInvoker(RpcfxResolver resolver){
        this.resolver = resolver;
    }

    public <T> RpcfxResponse invoke(RpcfxRequest<T> request) {
        RpcfxResponse response = new RpcfxResponse();
        Class<T> serviceClass = request.getServiceClass();

        try {
            // 拿到服务端的类
            T service = resolver.resolve(serviceClass);

            // 反射拿到类的方法
            Method method = resolveMethodFromClass(serviceClass, request.getMethod());

            // 调用实现类的方法
            Object result = method.invoke(service, request.getParams());

            // 两次json序列化能否合并成一个
//            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setResult(result);
            response.setStatus(true);
            return response;
        } catch ( IllegalAccessException | InvocationTargetException e) {
            response.setException(new RpcfxException(e.getMessage()));
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
