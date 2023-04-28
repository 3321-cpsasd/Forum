package com.pix.forum.dao;

import com.pix.forum.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author pix
 * @Date 2023/4/28 15:14
 */
@Mapper
public interface DiscussPostMapper {
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //@Param注解在方法只有一个参数且在<if>中使用必须使用
    int selectDiscussPostRows(@Param("userId") int userId);



}
