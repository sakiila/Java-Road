学习笔记

### 本周作业

第 22 课作业

1. （选做）命令行下练习操作Redis的各种基本数据结构和命令。
2. （选做）分别基于jedis，RedisTemplate，Lettuce，Redission实现redis基本操作 的demo，可以使用spring-boot集成上述工具。
3. （选做）spring集成练习:
    1. 实现update方法，配合@CachePut
    2. 实现delete方法，配合@CacheEvict
    3. 将示例中的spring集成Lettuce改成jedis或redisson。
4. （必做）基于Redis封装分布式数据操作：
    1. 在Java中实现一个简单的分布式锁；
    2. 在Java中实现一个分布式计数器，模拟减库存。
5. 基于Redis的PubSub实现订单异步处理

第 21 课作业
1. （必做）配置redis的主从复制，sentinel高可用，Cluster集群。
提交如下内容到github：
1）config配置文件，
2）启动和操作、验证集群下数据读写的命令步骤。
2. （选做）练习示例代码里下列类中的作业题：
08cache/redis/src/main/java/io/kimmking/cache/RedisApplication.java
3. （挑战☆）练习redission的各种功能；
4. （挑战☆☆）练习hazelcast的各种功能；
5. （挑战☆☆☆）搭建hazelcast 3节点集群，写入100万数据到一个map，模拟和演
示高可用，测试一下性能；