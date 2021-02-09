package io.kimmking.rpcfx.server;

public interface RpcfxResolver {

    <T> T resolve(Class<T> clazz);

}
