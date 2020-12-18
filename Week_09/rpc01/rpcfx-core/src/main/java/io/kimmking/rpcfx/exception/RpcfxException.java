package io.kimmking.rpcfx.exception;

/**
 * 异常数据
 */
public class RpcfxException extends RuntimeException {

    public RpcfxException(String message) {
        super(message);
    }

    public RpcfxException(String message, Throwable cause) {
        super(message, cause);
    }

}
