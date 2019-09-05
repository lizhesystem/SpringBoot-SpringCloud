package com.ylsoft.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ylsoft.client.Userclient;
import com.ylsoft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/clent/user")
//我们刚才把fallback写在了某个业务方法上，如果这样的方法很多，那岂不是要写很多。所以我们可以把Fallback配置加在类上，实现默认fallback：
//@DefaultProperties(defaultFallback = "fallBackMethod")  // 指定一个类的全局熔断方法
public class UserController {

//    @Autowired
//    private RestTemplate restTemplate;
    // 不要了

    @Autowired
    private Userclient userclient;

//    @Autowired
//    private DiscoveryClient discoveryClient; // eureka客户端，包含了拉取所有的eureka中服务的信息

//    @GetMapping
//    @ResponseBody
//    public User queryUserById(@RequestParam("id") Integer id) {
//        User user = this.restTemplate.getForObject("http://localhost/user/id/" + id, User.class);
//        return user;
//    }

    @ResponseBody
    @GetMapping
    // 定义如果出现降级访问的方法
//    @HystrixCommand(fallbackMethod = "queryUserByIdFallBack")
//    @HystrixCommand
    public String queryUserById(@RequestParam("id") Integer id) {
        // 获取服务提供方的信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        // 得到的是一个数组默认只有一个服务，取0第一个
//        ServiceInstance Instance = instances.get(0);
        // 拼接服务提供方访问地址
//        String baseUrl = "http://" + Instance.getHost() + ":" + Instance.getPort() + "/user/id/"+ id;

        // 为了能够精确控制请求的成功或失败，我们在consumer的调用业务中加入一段逻辑：
        if (id == 1) {
            throw new RuntimeException("忙忙忙");
        }

        // 修改调用方式,不用通过discoveryClient获取IP端口，直接调用eureka里注册的服务名即可。
//        String baseUrl = "http://service-provider/user/id/" + id;
        // 时候restTemplate访问服务
        // 这里就不返还对象了。和熔断的降级逻辑返还的一致，都返还字符串。
//        String user = this.restTemplate.getForObject(baseUrl, String.class);
        User user = this.userclient.queryById(id);
        return user.toString();
    }

    // 这是熔断的降级逻辑，出现熔断降级执行这个方法，注意：这个方法要和主方法相同的参数列表和返回值声明
//    public String queryUserByIdFallBack(Integer id) {
//        return "请求繁忙,请稍后再试";
//    }


    // 默认降级方法，不用任何参数，以匹配更多方法，但是返回值类型一定一致
    public String fallBackMethod() {
        return "请求繁忙,请稍后再试";
    }
}
