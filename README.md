# copyproject
摘抄别人的项目，作为联系

# 搜云库技术团队微信号: souyunku
从上面微信号找的练习项目
## [spring-kafka](https://github.com/spring-projects/spring-kafka)
### SpringBoot 整合 Kafka
#### 最小应用
1. 忽略下面
```
Could not autowire. No beans of 'KafkaTemplate<Object, Object>' type found. 
```
2. 引入spring-kafka
3. 配置文件编写server 地址
4. 注入kafatemplate
5. 调用template的send接口
6. 编写监听方法
#### [SpringBoot-Kafka](https://blog.csdn.net/a2267378/article/details/88595522)

1. 创建消费者和生产者的Map配置
2. 根据Map配置创建对应的消费者工厂(consumerFactory)和生产者工厂(producerFactory)
3. 根据consumerFactory创建监听器的监听器工厂
4. 根据producerFactory创建KafkaTemplate(Kafka操作类)
5. 创建监听容器