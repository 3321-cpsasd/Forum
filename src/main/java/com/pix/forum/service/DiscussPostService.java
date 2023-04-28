package com.pix.forum.service;

import com.pix.forum.dao.DiscussPostMapper;
import com.pix.forum.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author pix
 * @Date 2023/4/28 15:38
 */
@Service
public class DiscussPostService {
    private DiscussPostMapper discussPostMapper;

    @Autowired
    public DiscussPostService(DiscussPostMapper discussPostMapper) {
        this.discussPostMapper = discussPostMapper;
    }

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
