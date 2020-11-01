# netty-gateway

# Netty

网络应用开发框架

1. 异步
2. 事件驱动
3. 基于NIO

基本概念

- Channel

    通道，Java NIO 中的基础概念，代表一个打开的连接,可执行读取/写入IO 操作。
    Netty 对Channel 的所有IO 操作都是非阻塞的。

- ChannelFuture

    Java 的Future 接口，只能查询操作的完成情况, 或者阻塞当前线程等待操作完成。
    Netty 封装一个ChannelFuture 接口。
    我们可以将回调方法传给ChannelFuture，在操作完成时自动执行。

- Event & Handler Netty

    基于事件驱动，事件和处理器可以关联到入站和出站数据流。

- Encoder & Decoder

    处理网络IO 时，需要进行序列化和反序列化, 转换Java 对象与字节流。
    对入站数据进行解码，基类是ByteToMessageDecoder。
    对出站数据进行编码，基类是MessageToByteEncoder。

- ChannelPipeline

    数据处理管道就是事件处理器链。
    有顺序、同一Channel 的出站处理器和入站处理器在同一个列表中。

# 关键对象

Bootstrap: 启动线程，开启socket
EventLoopGroup: 线程池
EventLoop: 线程
SocketChannel: 连接
ChannelInitializer: 初始化
ChannelPipeline: 处理器链
ChannelHandler: 处理器
（BECH）

# Netty 优化

1、不要阻塞 EventLoop

2、系统参数优化
ulimit -a； /proc/sys/net/ipv4/tcp_fin_timeout，TcpTimedWaitDelay（减少2MSL时间）

3、缓冲区优化
SO_RCVBUF/SO_SNDBUF/SO_BACKLOG/REUSEXXX

4、心跳频率周期优化
心跳机制与断线重连

5、内存与ByteBuffer 优化
DirectBuffer与HeapBuffer

6、其他优化
-ioRatio； -Watermark（水位）； -TrafficShaping（流控）

# API网关

**流量网关**
关注稳定与安全
l 全局性流控
l 日志统计
l 防止SQL 注入
l 防止Web 攻击
l 屏蔽工具扫描
l 黑白IP 名单
l 证书/加解密处理

OpenResty、Kong

**业务网关**
提供更好的服务
l 服务级别流控
l 服务降级与熔断
l 路由与负载均衡、灰度策略
l 服务过滤、聚合与发现
l 权限验证与用户等级策略
l 业务规则与参数校验
l 多级缓存策略

Spring Cloud Gateway、Zuul2