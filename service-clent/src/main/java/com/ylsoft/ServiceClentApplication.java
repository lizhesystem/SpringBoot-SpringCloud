package com.ylsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker  开启熔断hystrix
// Spring就提供了一个组合注解：@SpringCloudApplication 替换上面3个
@SpringCloudApplication
@EnableFeignClients // 开启feign客户端
public class ServiceClentApplication {



    // 首先在引导类中注册RestTemplate
//    @Bean
//    @LoadBalanced
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
    // feign已经自动集成了Ribbon负载均衡的RestTemplate。所以，此处不需要再注册RestTemplate。


    public static void main(String[] args) {
        SpringApplication.run(ServiceClentApplication.class, args);
    }

}
