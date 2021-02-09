package io.kimmking.rpcfx.api;

import io.kimmking.rpcfx.exception.RpcfxException;
import lombok.Data;

/**
 * 响应数据
 */
@Data
public class RpcfxResponse {

    private Object result;

    private Boolean status;

    private RpcfxException exception;

}
