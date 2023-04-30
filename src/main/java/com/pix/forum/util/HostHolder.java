package com.pix.forum.util;

import com.pix.forum.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Author pix
 * @Date 2023/4/29 18:31
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUsers(User user) {
        users.set(user);
    }

    public void clear(){
        users.remove();
    }
}
