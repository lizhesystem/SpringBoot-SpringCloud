package com.ylsoft.dao;

import com.ylsoft.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// mapper注解能定义该接口是一个mybatis的mapper接口，能被spring容器扫描到
@Mapper
public interface UserDao {

    List<User> queryList();

    User findById(Integer id);
}
