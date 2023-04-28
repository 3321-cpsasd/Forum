package com.pix.forum.dao;

import com.pix.forum.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author pix
 * @Date 2023/4/26 22:10
 */
@Mapper
public interface UserMapper {
    User selectById(int id);
    User selectByName(String name);
    User selectByEmail(String email);
    int insertUser(User user);
    int updateStatus(int id, int status);
    int updateHeader(int id,String headerUrl);
    int updatePassword(int id,String password);

}
