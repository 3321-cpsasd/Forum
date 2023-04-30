package com.pix.forum.controller;

import com.pix.forum.entity.Comment;
import com.pix.forum.entity.DiscussPost;
import com.pix.forum.entity.Page;
import com.pix.forum.entity.User;
import com.pix.forum.service.CommentService;
import com.pix.forum.service.DiscussPostService;
import com.pix.forum.service.UserService;
import com.pix.forum.util.CommunityConstant;
import com.pix.forum.util.CommunityUtil;
import com.pix.forum.util.HostHolder;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author pix
 * @Date 2023/4/30 16:12
 */
@Controller
@RequestMapping("/discuss")
public class DiscussPostController implements CommunityConstant {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;
    @Resource
    private CommentService commentService;

    @PostMapping("/add")
    @ResponseBody
    public String addDiscussPost(String title,String content){
        User user = hostHolder.getUser();
        if(user == null){
            return CommunityUtil.getJsonString(403,"你还没有登录");
        }

        DiscussPost discussPost = new DiscussPost();
        discussPost.setUserId(user.getId());
        discussPost.setTitle(title);
        discussPost.setContent(content);
        discussPost.setCreateTime(new Date());
        discussPostService.addDiscussPost(discussPost);

        //报错情况将来一起处理

        return CommunityUtil.getJsonString(0,"发布成功！");
    }

    @GetMapping("/detail/{discussPostId}")
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model, Page page){
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        model.addAttribute("post",post);
        //作者
        User user = userService.findUserById(post.getUserId());
        model.addAttribute("user",user);

        page.setLimit(5);
        page.setPath("discuss/detail/" + discussPostId);
        page.setRows(post.getCommentCount());

        List<Comment> commentsList = commentService.findCommentsByEntity(ENTITY_TYPE_POST, post.getId(),
                                                                        page.getOffset(), page.getLimit());
        List<Map<String,Object>> commentVoList = new ArrayList<>();
        if(commentsList != null){
            for (Comment comment : commentsList) {
                Map<String,Object> commentVo = new HashMap<>();
                //评论
                commentVo.put("comment",comment);
                //评论作者
                commentVo.put("user",userService.findUserById(comment.getId()));

                //回复列表
                List<Comment> replyList = commentService.findCommentsByEntity(ENTITY_TYPE_COMMENT, comment.getId(), 0, Integer.MAX_VALUE);
                //回复的Vo列表
                List<Map<String,Object>> replyVoList = new ArrayList<>();
                if(replyList != null){
                    for (Comment reply : replyList) {
                        Map<String,Object> replyVo = new HashMap<>();
                        replyVo.put("reply",reply);
                        replyVo.put("user",userService.findUserById(reply.getId()));
                        User target = reply.getTargetId() == 0 ? null:userService.findUserById(reply.getTargetId());
                        replyVo.put("target",target);
                        replyVoList.add(replyVo);
                    }
                }
                commentVo.put("replys",replyVoList);

                int replyCount = commentService.findPostCommentCountByUserId(comment.getUserId(),ENTITY_TYPE_COMMENT);
                commentVo.put("replyCount",replyCount);

                commentVoList.add(commentVo);
            }
        }
        model.addAttribute("comment",commentVoList);

        return "/site/discuss-detail";
    }
}
