# README
> create by nohi 20220606

Spring Cloud alibaba 学习

## 历程
### 20220606
* nacos
  * 服务发现
    * ap监控endpoint http://localhost:8081/actuator/nacosdiscovery
  * 配置中心
    * apserver-one-dev.yaml
    * ap监控config: http://localhost:8081/actuator/nacosconfig
  ```yaml
  ap:
    int-val: 1
    str-val: this is nohi
    double-val: 1.01
  user:
    name: "this is nohi"
    age: 36
  ```
### 20220622
* dubbo使用
```yaml
dubbo-model-api         共用模块工程
dubbo-service-customer  消费者
dubbo-service-provider  生产者
```
* 问题：
  1. 未集成dubbo admin
     参考：`https://blog.csdn.net/g5zhu5896/article/details/122588793`
