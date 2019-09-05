package com.ylsoft.client.client;

import com.ylsoft.client.Userclient;
import com.ylsoft.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBack implements Userclient {

    @Override
    public User queryById(Integer id) {
        User user = new User();
        user.setName("服务器开了个小差");

        return user;
    }
}
