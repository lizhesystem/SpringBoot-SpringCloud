package com.ylsoft.controller;

import com.ylsoft.pojo.User;
import com.ylsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @RestController是@ResponseBody和@Controller的组合注解。
// @Controller 用来响应页面，@Controller必须配合模版来使用,直接返回对象不行,使用@RestController返回json格式字符串。
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 查询所有
    @RequestMapping("/query")
    public List<User> findAll() {
        return this.userService.findAll();
    }

    // 查询单个
    @RequestMapping("/id/{id}")
    public User findById(@PathVariable("id") Integer id) {
        return this.userService.findById(id);
    }
}
