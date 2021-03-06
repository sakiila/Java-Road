# 毕业总结

## 靡不有初，鲜克有终

工作快两年了，我仍在成长的路上踽踽独行。毕业之后的某一段日子，我曾苦恼于如何去实现一个技术需求，也曾彷徨于自己脚下的路是否正确，我在夜深人静的出租屋里默默流过泪，也在公司的卫生间里长时间地思考这一切的意义。

我做程序员的意义在哪？看了看周围，没有答案，跟着社会大流一起挤进这个能拿高薪但极度内卷的岗位，在各个学习平台上增加自己的焦虑，顶着干涩发红的双眼敲下一行行低级代码。

后来呢，我也学会了如何在这急躁的生活中苟延残喘，磨圆自己的棱角，不去触碰他人的喜怒哀乐。结果呢，我丧失了努力的欲望，于是丢掉了来之不易的晋升资格和优秀评选。我哭过，绝望过，也曾想过从身边的落地窗跳下十三层楼。我不想随波逐流，但又渴望出人头地。那出人头地之后的意义是什么？生活的意义又是什么？

那一天，我照旧翻看着手机，被动地吸收着那些推文里杂乱无章的知识。好友发送了一条消息，推荐了一门正在报名阶段的线上课程，我以为又是贩卖焦虑的营销手段。浏览下目录，正是我极度需要补充的知识大纲，能够帮我从一个初级程序员引导到一条正确的方向。

下单，等待，开课。

听课，笔记，收获。

深圳，相聚，交谈。

“我很看好你们，还这么年轻。”临别时，老师笑着对我和朋友说。走在回家的路上，环顾着深夜的车水马龙，迎面而来秋末的风，原来这么温暖。

“生活有意义吗？没有。人这一生的意义就在于，要用一辈子的时光来寻找一个意义。”

“接纳自己是人生蜕变的最大里程碑，没有之一。”

我从迷雾中走出，秦老师和崔老师高举着手电筒指引着那条路，那条空空如也平平无奇的路，那条我曾蒙蔽双眼苦苦寻找的路，那条我这一生成长必须要走的路。

完。

## 学习路线及推荐课程

自己在课下整理的知识点，作为课外补充。

→ JVM（极客深入拆解Java虚拟机）

→ Netty（极客Netty源码剖析与实战）

→ 并发编程（极客Java并发编程实战）

→ Spring（极客小马哥讲Spring、Spring源码深度解析）

→ Spring Boot（慕课小马哥Spring Boot2.0深度实践）

→ Java Stream

→ 设计模式（极客设计模式之美）

→ 单元测试

→ MySQL（极客MySQL实战45讲）

→ 分库分表（ShardingSphere项目）

→ 分布式事务（极客分布式协议与算法）

→ RPC（极客网络编程实战、极客RPC实战与核心原理）

→ Dubbo（拉勾Dubbo源码解读）

→ Spring Cloud

→ Redis（极客Redis核心技术与实战）

→ Kafka（极客Kafka核心技术与实战）

→ 架构方法论

## 学习总结

### 1 JVM

小时候，玩厌了遥控车以后，会找把螺丝刀，拆开看看它的内部，是不是真的有个小人坐在里面握着方向盘。学习了 Java 之后，不能浮于 API 的表面，要拆开它，去了解类文件、字节码、内存模型等等。

编写完的 Java 文件，会通过编译器转为字节码文件，再由虚拟机转为机器码。通过类加载机制，Java 进程在 JVM 中的栈和堆结构中执行与读写数据，GC 线程清理垃圾对象，构成一整个运行环境。

如果程序生病了，我们也会借助 JVM 命令行等工具分析与诊断原因。

### 2 NIO

程序很孤单，它也需要和其他程序交流。我们有电话，它有什么呢？它有 IO（Input / Output）。

BIO 呢，像八零年代的书信。车马很远，书信很慢（其实车马很快，回信很慢）。后来有了 NIO，像 BB 机，你收到短信后，要去公用电话亭回复。再后来呢，有了 AIO，像智能手机，早上起床后“在吗？你在干嘛？我在想你。”，十分钟后收到“我也是”。

不得不提到网络框架的翘楚 Netty，能学会就尽量学会。

### 3 并发编程

分工合作提高生产力。

死锁、多线程、并发容器、原子类等等，实在太多了。作为程序员的我们，其实主要关注 Executor 相关的线程池操作就够了。多线程带来的弊端大于管理它的精力。

### 4 Spring 和 ORM 等框架

Spring 其实看起来很庞大，但是简单地理解，它只是主要帮助程序员去做 Bean 的管理工作，类似许许多多的 starter 构件也是一个整合包，减轻开发的压力。

### 5 MySQL 数据库和 SQL

在这个数据为王的时代，数据库好像是唯一不能做到无状态的工具。

为什么要学习数据库呢？SQL 是那最后一公里，不管前面优化得有多极致，一句慢查询就可能是压死骆驼的稻草。

从分析 InnoDB 引擎的索引结构，到学习复杂的事务机制，再到大数据集下的读写分离、分库分表策略，还有难懂的分布式事务。多了解一点 MySQL，我们就能多写出一些健壮的 SQL，稳固我们的程序。

### 6 分库分表

分库分表是为了解决容量问题，提升整个系统的数据容量上限。

首先是垂直拆分，竖着砍一刀，也可以理解为业务拆分。分成一个一个领域模型，帮助业务增长。

其次是水平拆分，横着砍一刀，减轻单库单表的压力。提高系统的稳定性和负载能力。

### 7 RPC 和微服务

RPC 像什么呢？像两个亲密的人，躺在同一张沙发上，互相给对方发微信，就像面对面聊天似的。

RPC 解决了微服务之间的调用问题，不需要程序操心另一段程序的位置在哪，也不用操心是否语言不通。

市面上有种类繁多的 RPC 框架，像早期的 WebService、现在的 Dubbo、Thrift 等等。

### 8 分布式缓存

Redis 是我最喜欢的一款中间件，小巧精致，易学易懂。当然，要在生产环境用好，还是需要下一番功夫的。

### 9 分布式消息队列

有一天，前端小妹妹问我，Kafka 中间件是什么？我很喜欢举这个例子，你写了一封情书，想通过邮局寄给心上人，邮局就是这个中间人，这个默默帮你传情达意的中间人。

