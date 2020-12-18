package io.kimmking.rpcfx.client.callable;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

public class OkHttpCallable implements RpcfxCallable {

    private OkHttpClient okHttpClient;

    public OkHttpCallable() {
        this.okHttpClient = new OkHttpClient();
    }

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    @Override
    public RpcfxResponse call(RpcfxRequest rpcfxRequest, String url) {
        String reqJson = JSON.toJSONString(rpcfxRequest);
        System.out.println("req json: " + reqJson);

        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();

        try {
            String respJson = okHttpClient.newCall(request).execute().body().string();
            System.out.println("resp json: " + respJson);
            return JSON.parseObject(respJson, RpcfxResponse.class);
        } catch (IOException e) {
            throw new RpcfxException(e.getMessage());
        }
    }

}
