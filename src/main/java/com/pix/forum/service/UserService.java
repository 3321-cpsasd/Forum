package com.pix.forum.service;

import com.pix.forum.dao.UserMapper;
import com.pix.forum.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author pix
 * @Date 2023/4/28 15:44
 */
@Service
public class UserService{
    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
