package com.ylsoft.client;

import com.ylsoft.client.client.UserClientFallBack;
import com.ylsoft.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// 定义一个接口，feign会通过动态代理帮我们生成实现类
@FeignClient(value = "service-provider",fallback = UserClientFallBack.class)  // 注解标记该类是个feign接口
public interface Userclient {

    // 按照springMVC的方式在接口里写方法，方法的参数其实就是指向之前service-provider里的方法
    // 原先访问服务端地址是 " http://service-provider/user/id/" + id;"
    @GetMapping("user/id/{id}")
    User queryById(@PathVariable("id") Integer id);
}
