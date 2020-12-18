package io.kimmking.rpcfx.client.callable;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;

public interface RpcfxCallable {

    RpcfxResponse call(RpcfxRequest request, String url);
}
