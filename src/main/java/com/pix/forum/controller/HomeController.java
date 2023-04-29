package com.pix.forum.controller;

import com.pix.forum.entity.DiscussPost;
import com.pix.forum.entity.Page;
import com.pix.forum.entity.User;
import com.pix.forum.service.DiscussPostService;
import com.pix.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author pix
 * @Date 2023/4/28 15:50
 */
@Controller
public class HomeController {
    private DiscussPostService discussPostService;
    private UserService userService;

    @Autowired
    public HomeController(DiscussPostService discussPostService, UserService userService) {
        this.discussPostService = discussPostService;
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getIndexPage(Model model,Page page){
        // 方法调用之前SpringMVC会自动实例化Model和page
        //thymeleaf可以自动访问
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if(list != null){
            for (DiscussPost post : list) {
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
