# SpringBoot-SpringCloud入门案例
Springboot的入门案例集成`eureka`注册中心、`ribbon`负载均衡、`hystrix`熔断保护、`feign`请求伪装、`zuul`网关。

微服务最好是基于springboot搭建，所以项目里的单个微服务都是基于springboot搭建。
# SpringBoot

![image](https://user-images.githubusercontent.com/35909339/64346243-5a116400-d024-11e9-8cc1-916df8b7bd02.png)

## 入门：
- pom.xml配置
使用IDEA的`spring initializr`可以快速搭建，当前也可以创建maven工程后导入依赖包。
注意下面的pom配置

```xml
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.6.RELEASE</version>
        <relativePath/> <!-- 这里注意下版本 -->
    </parent>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Finchley.SR2</version>   <!-- 这里注意下这个是正式版 Finchley-->
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
```

目录结构参考该项目`service-provider` 微服务。使用的是IDEA快速搭建生成
目前该例子使用的是mybatis的xml配置SQL的方式，如果需要使用**通过mapper**的话需要参考下面

## 通用mapper

1. 导入pom.xml
```xml
<dependency>
    <groupId>tk.mybatis</groupId>
    <artifactId>mapper-spring-boot-starter</artifactId>
    <version>2.0.4</version>
</dependency>
```
2. 配置文件
```yml
mybatis:
  type-aliases-package: cn.itcast.service.pojo
  # mapper-locations: classpath:mappers/*.xml  因为没有用xml这行注释掉
```
3. 实体类配置
```java
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 用户名
    private String userName;
    // 下面内容省略
```
4. 定义接口，集成的这个接口里面很多增删改查的方法，不用自己写SQL了，直接调用默认的即可。
```java
@Mapper
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{
}
``` 
5. 定义service，剩下的操作使用controller直接调用。
```java
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id) {
        //  在这里使用通过mapper的方法
        return this.userMapper.selectByPrimaryKey(id);
    }
}
```


# SpringCloud架构

![image](https://user-images.githubusercontent.com/35909339/64350642-95636100-d02b-11e9-9068-4f760363c094.png)
