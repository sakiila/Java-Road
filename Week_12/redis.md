# Redis主从配置

### 拉取redis镜像
`docker pull redis`

### 启动容器
```
docker run --name redis-6379 -p 6379:6379 -d redis 
docker run --name redis-6380 -p 6380:6379 -d redis 
docker run --name redis-6381 -p 6381:6379 -d redis
```

### 查看容器ip
`docker inspect xxx`

redis-6379 172.17.0.2:6379 

redis-6380 172.17.0.3:6379

redis-6381 172.17.0.4:6379

### 执行SLAVEOF
`SLAVEOF 172.17.0.2 6379`

# Redis配置哨兵

### 编辑配置文件
```
touch /sentinel.conf
echo 'sentinel monitor mymaster 172.17.0.2 6379 1' > /sentinel.conf
```

### 启动哨兵
`redis-sentinel /sentinel.conf`

### 关闭主节点，查看哨兵日志
新的master是172.17.0.3
```shell
27:X 06 Jan 2021 10:18:24.257 # Sentinel ID is d3c6d83aa955dec26d51a38462a7443fd053903d
27:X 06 Jan 2021 10:18:24.257 # +monitor master mymaster 172.17.0.2 6379 quorum 1
27:X 06 Jan 2021 10:18:24.257 * +slave slave 172.17.0.4:6379 172.17.0.4 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:18:24.261 * +slave slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:18:24.536 * +sentinel sentinel fc634255d4cc39ba90994945fbcec487a597fb9b 172.17.0.2 26379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:20:06.017 * +sentinel sentinel a474ca4daaf8921936f119788343572f85498abf 172.17.0.3 26379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.136 # +sdown master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.136 # +odown master mymaster 172.17.0.2 6379 #quorum 1/1
27:X 06 Jan 2021 10:26:40.136 # +new-epoch 1
27:X 06 Jan 2021 10:26:40.136 # +try-failover master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.142 # +vote-for-leader d3c6d83aa955dec26d51a38462a7443fd053903d 1
27:X 06 Jan 2021 10:26:40.142 # +sdown sentinel fc634255d4cc39ba90994945fbcec487a597fb9b 172.17.0.2 26379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.149 # a474ca4daaf8921936f119788343572f85498abf voted for d3c6d83aa955dec26d51a38462a7443fd053903d 1
27:X 06 Jan 2021 10:26:40.200 # +elected-leader master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.200 # +failover-state-select-slave master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.253 # +selected-slave slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.253 * +failover-state-send-slaveof-noone slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.353 * +failover-state-wait-promotion slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.778 # +promoted-slave slave 172.17.0.3:6379 172.17.0.3 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.778 # +failover-state-reconf-slaves master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:40.835 * +slave-reconf-sent slave 172.17.0.4:6379 172.17.0.4 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:41.849 * +slave-reconf-inprog slave 172.17.0.4:6379 172.17.0.4 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:41.849 * +slave-reconf-done slave 172.17.0.4:6379 172.17.0.4 6379 @ mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:41.905 # +failover-end master mymaster 172.17.0.2 6379
27:X 06 Jan 2021 10:26:41.905 # +switch-master mymaster 172.17.0.2 6379 172.17.0.3 6379
27:X 06 Jan 2021 10:26:41.905 * +slave slave 172.17.0.4:6379 172.17.0.4 6379 @ mymaster 172.17.0.3 6379
27:X 06 Jan 2021 10:26:41.905 * +slave slave 172.17.0.2:6379 172.17.0.2 6379 @ mymaster 172.17.0.3 6379
27:X 06 Jan 2021 10:27:11.921 # +sdown slave 172.17.0.2:6379 172.17.0.2 6379 @ mymaster 172.17.0.3 6379
```