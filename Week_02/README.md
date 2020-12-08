学习笔记

### 本周作业
按今天的课程要求,实现一个网关,基础代码可以 fork:https://github.com/kimmking/JavaCourseCodes/02nio/nio02 文件夹下实现以后,代码提交到 Github。
1. *周四作业(必做):整合你上次作业的 httpclient/okhttp;
    - 压测：调整NettyServer和HttpClient的参数
2. 周四作业(可选):使用 netty 实现后端 http 访问(代替上一步骤);
    - 压测：调整NettyServer和NettyClient的参数
3. *周六作业（必做）:实现一个request的过滤器filter。
    - filter里把我们请求的http头拿到，然后在里面添加一个新的key-value，key是nio，value是自己的名字拼音。
    - 实际请求后端服务的时候，把这些请求所有的头拿出来，在实际调用后端服务之前全部添加到对后端的请求头里。（我们去调用后端服务的时候，那个请求里面就比网关接入请求多一个我们自定义的头。这样就相当于我们的程序在网关的filter这边做了一个加强）
4. 周六作业(可选):实现路由
    - 把现在只代理的一个后端服务，变成了可以代理多个后端服务。我们通过最简的路由算法，比如说随机的对后端的两个服务取一个，然后调用这个服务返回数据。

之后可以进行更多的扩展：
1. 定义一个过滤响应数据和报文头的resposeFilter
2. 针对用户的过滤可以自定义一个filter
3. 针对安全，可以加密解密token的filter
4. 针对高并发限流，可以调节线程池，也可以加限流算法的filter
5. 针对后端服务的负载均衡，可以加roundribbon或者权重的算法等等

# GC日志分析

命令 java -XX:+PrintGCDetails GCLogAnalysis

-Xloggc:gc.demo.log（导出日志）-XX:+PrintGCDateStamps（打印时间戳）

# 默认情况

```
[GC (Allocation Failure)
[PSYoungGen: 65536K->10742K(76288K)] 65536K->21628K(251392K), 0.0040638 secs]
[Times: user=0.03 sys=0.06, real=0.00 secs]
```

GC 表示年轻代GC

Allocation Failure 表示GC原因，分配空间失败（空间不足）

PSYoungGen: 65536K->10742K(76288K)  年轻代GC前后大小，括号内为年轻代大小

65536K->21628K(251392K)  整个堆GC前后大小，括号内为整个堆大小

user 用户线程时间

sys 系统线程时间

real 真正GC的时间

> 可以看到，第一次GC时，堆大小就是年轻代大小（65536K）。

```
[Full GC (Ergonomics)
[PSYoungGen: 10751K->0K(272896K)] 
[ParOldGen: 120961K->121417K(262144K)] 131712K->121417K(535040K), 
[Metaspace: 2637K->2637K(1056768K)], 0.0150500 secs] 
[Times: user=0.00 sys=0.00, real=0.02 secs]
```

Full GC

默认使用的是ParallelGC

# 串行GC

-XX:+UseSerialGC

```
[GC (Allocation Failure) 
[DefNew: 139776K->17472K(157248K), 0.0210434 secs] 
139776K->47171K(506816K), 0.0213852 secs] 
[Times: user=0.00 sys=0.02, real=0.02 secs]
```

DefNew 表示年轻代

> 设置-Xmx2048k以下都会提示
Error occurred during initialization of VM
GC triggered before VM initialization completed. Try increasing NewSize, current value 640K.
计算 640 * 3 = 1920 < 2048
堆空间需要大于2048K，2M

# 并行GC

 -XX:+UseParallelGC

> 设置-Xmx4096k以下都会提示
Error occurred during initialization of VM
GC triggered before VM initialization completed. Try increasing NewSize, current value 1536K.
计算 1536 * 3 = 4608 > 4096
堆空间需要大于 4096K，4M