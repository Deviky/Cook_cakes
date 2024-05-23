package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @Autowired
    private PostService postService;
    @GetMapping("/")
    String home(Model model){
        model.addAttribute("posts", postService.findAllPosts());
        model.addAttribute("popular_posts", postService.findAllPopularPosts());
        return "index";
    }
}
