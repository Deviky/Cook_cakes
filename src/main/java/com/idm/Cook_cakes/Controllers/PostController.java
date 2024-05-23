package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Models.Post;
import com.idm.Cook_cakes.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @GetMapping("/{postId}")
    String home(@PathVariable long postId, Model model){
        Post post = postService.findPostById(postId);
        List<String> ingredients = Arrays.stream(post.getIngredients().split(",")).toList();
        int totalTime = post.getPrepTime() + post.getCookTime();
        List<String> theProcesses = Arrays.stream(post.getTheProcess().split("\n")).toList();
        model.addAttribute("post", post);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("theProcesses", theProcesses);
        model.addAttribute("totalTime", totalTime);
        return "post";
    }
}
