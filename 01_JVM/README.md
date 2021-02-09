学习笔记

Java 是一种面向对象、静态类型、编译执行，有 VM/GC 和运行时、二进制跨平台的高级语言。

> 二进制跨平台：.java文件编译成.class文件，分发在不同的平台上，都能运行。

# 字节码

Java bytecode 由单字节（byte）的指令组成，理论上最多支持256 个操作码（opcode）。

根据指令的性质，主要分为四个大类：

1. 栈操作指令，包括与局部变量交互的指令
2. 程序流程控制指令
3. 对象操作指令，包括方法调用指令
4. 算术运算以及类型转换指令

> 表现形式为助记符，例如aload、astore。

# 字节码的运行时结构

JVM 是一台基于栈的计算机器。
每个线程都有一个独属于自己的线程栈（JVM Stack），用于存储栈帧（Frame）。
**每一次方法调用，JVM 都会自动创建一个栈帧。**
**栈帧由操作数栈，局部变量数组以及一个Class 引用组成。**Class 引用指向当前方法在运行时常量池中对应的Class。

（在栈上做计算）

Stack  -》 sotre -》Local Variable

Stack 《- load《- Local Variable 

### 举个栗子 🌰

istore 2：将2号slot位置上的int类型数据，store到局部变量数组。

iconst_1：声明一个int类型常量，值为1，放在栈上。（如果值过大，用两个字节显示，如dstore 4）

i2d：int类型强转为double类型。

invokespecial：用来调用构造函数，但也可以用于调用同一个类中的private 方法，以及可见的超类方法。

# 类的生命周期

1. 加载（Loading）：找Class 文件
2. 验证（Verification）：验证格式、依赖
3. 准备（Preparation）：静态字段、方法表
4. 解析（Resolution）：符号解析为引用
5. 初始化（Initialization）：构造器、静态变量赋值、静态代码块
6. 使用（Using）
7. 卸载（Unloading）

### 类的加载时机

1. 当虚拟机启动时，初始化用户指定的主类，就是启动执行的main 方法所在的类；
2. 当遇到用以新建目标类实例的new 指令时，初始化new 指令的目标类，就是new一个类的时候要初始化；
3. 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
4. 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
5. 子类的初始化会触发父类的初始化；
6. 如果一个接口定义了default 方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化；
7. 使用反射API 对某个类进行反射调用时，初始化这个类，其实跟前面一样，反射调用要么是已经有实例了，要么是静态方法，都需要初始化；
8. 当初次调用MethodHandle 实例时，初始化该MethodHandle 指向的方法所在的类。

### 不会初始化（可能会加载）

1. 通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。
2. 定义对象数组，不会触发该类（对象）的初始化。
3. 常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。
4. 通过类名获取Class 对象，不会触发类的初始化，Hello.class 不会让Hello 类初始化。
5. 通过Class.forName 加载指定类时，如果指定参数initialize 为false 时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。Class.forName（“jvm.Hello”）默认会加载Hello 类。
6. 通过ClassLoader 默认的loadClass 方法，也不会触发初始化动作（加载了，但是不初始化）。

# 三类加载器

> 箭头是指查找一个类的操作。

1. 启动类加载器（BootstrapClassLoader）
2. 扩展类加载器（ExtClassLoader）
3. 应用类加载器（AppClassLoader）

> 双亲是指除启动类加载器，其他加载器都有一个父类加载器。

### 加载器特点：

> 启动类加载器由C++实现，扩展类和应用类由Java实现。

1. 双亲委托
2. 负责依赖
3. 缓存加载

> JDK9以后，没有URL ClassLoader了。

### 添加引用类的几种方式

1、放到JDK 的lib/ext 下，或者java -Djava.ext.dirs=<目录>
2、java –cp/classpath 或者class 文件放到当前路径
3、自定义ClassLoader 加载
4、拿到当前执行类的ClassLoader，反射调用addUrl 方法添加Jar 或路径(JDK9 无效)。

# JVM 内存模型

## JVM 内存结构

每个线程都只能访问自己的线程栈。

每个线程都不能访问（看不见）其他线程的局部变
量。

所有原生类型的局部变量都存储在线程栈中，因此
对其他线程是不可见的。

线程可以将一个原生变量值的副本传给另一个线程，
但不能共享原生局部变量本身。

堆内存中包含了Java 代码中创建的所有对象，不
管是哪个线程创建的。其中也涵盖了包装类型
（例如Byte，Integer，Long 等）。

不管是创建一个对象并将其赋值给局部变量， 还
是赋值给另一个对象的成员变量， 创建的对象都
会被保存到堆内存中。

## 数据保存位置

方法中使用的原生数据类型和对象引用地址在栈上存储；对象、对象成员与类定义、静态变量在堆上。

## JVM 内存整体结构

每启动一个线程，JVM 就会在栈空间栈分配对应的线程栈，比如1MB 的空间（-Xss1m）。

线程栈也叫做Java 方法栈。如果使用了JNI 方法，则会分配一个单独的本地方法栈(Native Stack)。

## JVM 栈内存结构

PC：Program Counter Register程序计数器

class 指针（标识这个栈帧对应的是哪个类的方法,
指向非堆里面的Class 对象）

## JVM 堆内存结构

堆内存是所有线程共用的内存空间，JVM 将Heap 内存分为**年轻代**（Young generation）和**老年代**（Old generation, 也叫Tenured）两部分。

年轻代还划分为3 个内存池，**新生代**（Eden space）和**存活区**（Survivor space），在大部分GC 算法中有2 个存活区（S0，S1），在我们可以观察到的任何时刻，S0 和S1 总有一个是空的，但一般较小，也不浪费多少空间。

Non-Heap 本质上还是Heap，只是一般不归GC管理，里面划分为3 个内存池。

**Metaspace**，以前叫持久代（永久代，Permanent generation）, Java8 换了个名字叫Metaspace。

**CCS**, Compressed Class Space, 存放class 信息的，和Metaspace 有交叉。

**Code Cache**，存放JIT 编译器编译后的本地机器代码。

> Java8以后，运行时常量池和静态常量池在Metaspace中，字符串常量池在堆中。

# JVM 启动参数

1. 以-开头为标准参数，所有的JVM 都要实现这些参数，并且向后兼容。（-server）
2. -D 设置系统属性。（-Dfile.encoding=UTF-8）
3. 以-X 开头为非标准参数， 基本都是传给JVM 的，默认JVM 实现这些参数的功能，但是并不保证所有JVM 实现都满足，且不保证向后兼容。可以使用java -X 命令来查看当前JVM 支持的非标准参数。（-Xmx8g）
4. 以–XX：开头为非稳定参数, 专门用于控制JVM的行为，跟具体的JVM 实现有关，随时可能会在下个版本取消。（-XX:+UseG1GC）
5. -XX：+-Flags 形式，+- 是对布尔值进行开关。
6. -XX：key=value 形式，指定某个选项的值。（-XX:MaxPermSize=256m）

### 运行模式

-Xint：在解释模式（interpreted mode）下运行，-Xint 标记会强制JVM 逐条解释执行所有的字节码，这当然会降低运行速度，通常低10倍或更多。

-Xcomp：-Xcomp 参数与-Xint 正好相反，JVM 在第一次使用时会把所有的字节码编译成本地代码，从而带来最大程度的优化。【注意预热】

### 堆内存

Xmx （最大堆内存）需要与Xms （堆内存初始大小）设置相等，原因是：

堆内存扩容可能会导致性能抖动。

### 分析诊断

-XX：+-HeapDumpOnOutOfMemoryError 选项, 当OutOfMemoryError 产生，即内存溢出(堆内存或持久代)时，自动Dump 堆内存。
示例用法： java -XX:+HeapDumpOnOutOfMemoryError -Xmx256m ConsumeHeap
-XX：HeapDumpPath 选项，与HeapDumpOnOutOfMemoryError 搭配使用, 指定内存溢出时Dump 文件的目录。
如果没有指定则默认为启动Java 程序的工作目录。