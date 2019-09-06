[TOC]

# SpringBoot-SpringCloud
springboot的入门案例集成eureka注册中心、ribbon负载均衡、hystrix熔断保护、feign请求伪装、zuul网关

# SpringBoot

![image](https://user-images.githubusercontent.com/35909339/64346243-5a116400-d024-11e9-8cc1-916df8b7bd02.png)

目录结构参考该项目`service-provider` 微服务。使用的是IDEA快速搭建生成
**注意**
- 默认导入的时候修改springboot的版本号，还有发行版本选择`Finchley.SR2`。
- 例子里没用mybatis的通用mapper使用的原始的mapper.xml，留意mybatis的注解和配置就行了。
```
mybatis:
  # 定义mybatis要扫描的包路径和对应的xml路径
  type-aliases-package: com.ylsoft.pojo
  mapper-locations: classpath:mappers/*.xml
```
# SpringCloud架构

![image](https://user-images.githubusercontent.com/35909339/64350642-95636100-d02b-11e9-9068-4f760363c094.png)
