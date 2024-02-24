package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Models.Post;
import com.idm.Cook_cakes.Services.MyUserService;
import com.idm.Cook_cakes.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MyUserService myUserService;
    @Autowired
    PostService postService;
    @GetMapping("/home")
    String home(Model model){
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("popular_posts", postService.findAllPopularPosts());
        return "index";
    }
}
