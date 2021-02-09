package io.kimmking.rpcfx.api;

import lombok.Data;

/**
 * 请求数据
 * @param <T>
 */
@Data
public class RpcfxRequest<T> {

  private Class<T> serviceClass;

  private String method;

  private Object[] params;

}
