package com.pix.forum.service;

import com.pix.forum.dao.DiscussPostMapper;
import com.pix.forum.entity.DiscussPost;
import com.pix.forum.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @Author pix
 * @Date 2023/4/28 15:38
 */
@Service
public class DiscussPostService {
    private DiscussPostMapper discussPostMapper;
    private SensitiveFilter sensitiveFilter;
    @Autowired
    public DiscussPostService(DiscussPostMapper discussPostMapper, SensitiveFilter sensitiveFilter) {
        this.discussPostMapper = discussPostMapper;
        this.sensitiveFilter = sensitiveFilter;
    }




    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    public int addDiscussPost(DiscussPost discussPost){
        if(discussPost == null){
            throw new IllegalArgumentException("参数不能为空！");
        }

        discussPost.setTitle(HtmlUtils.htmlEscape(discussPost.getTitle()));
        discussPost.setContent(HtmlUtils.htmlEscape(discussPost.getContent()));

        //过滤敏感词
        discussPost.setTitle(sensitiveFilter.filter(discussPost.getTitle()));
        discussPost.setContent(sensitiveFilter.filter(discussPost.getContent()));

        return discussPostMapper.insertDiscussPost(discussPost);
    }

    public DiscussPost findDiscussPostById(int id){
        return discussPostMapper.selcetDiscussPostById(id);
    }

    public int updateCommentCount(int id,int commentCount){
        return discussPostMapper.updateCommentCount(id,commentCount);
    }


}
