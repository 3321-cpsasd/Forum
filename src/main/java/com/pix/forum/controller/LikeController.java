package com.pix.forum.controller;

import com.pix.forum.entity.User;
import com.pix.forum.service.LikeService;
import com.pix.forum.util.CommunityUtil;
import com.pix.forum.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author pix
 * @Date 2023/5/1 19:01
 */
@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private HostHolder hostHolder;

    @PostMapping(path="/like")
    @ResponseBody
    public String like(int entityType,int entityId,int entityUserId){
        User user = hostHolder.getUser();
        likeService.like(user.getId(),entityType,entityId,entityUserId);
        long likeCount = likeService.findEntityLikeCount(entityType,entityId);
        int likeStatus = likeService.findEntityLikeStatus(user.getId(),entityType,entityId);
        Map<String,Object> map = new HashMap<>();
        map.put("likeCount",likeCount);
        map.put("likeStatus",likeStatus);

        return CommunityUtil.getJsonString(0,null,map);
    }
}
