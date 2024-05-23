package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Models.Post;
import com.idm.Cook_cakes.Services.MyUserService;
import com.idm.Cook_cakes.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private PostService postService;
    @Autowired
    private MyUserService userService;
    @GetMapping("")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        MyUser user = userService.findByUsername(currentUsername);
        List<Post> posts = postService.findAllPostsByAuthor(user);
        model.addAttribute("posts",posts);
        model.addAttribute("name", user.getNickname());
        model.addAttribute("about", user.getAbout());
        model.addAttribute("postCount", posts.size());
        return "profile";
    }

}
