package com.ylsoft.service;

import com.ylsoft.dao.UserDao;
import com.ylsoft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    // 查询所有的方法
    public List<User> findAll() {
        return this.userDao.queryList();
    }

    // 查询单条数据
    public User findById(Integer id) {
        return this.userDao.findById(id);
    }
}
