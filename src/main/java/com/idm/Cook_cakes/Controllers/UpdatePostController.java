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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/update")
public class UpdatePostController {

    @Autowired
    private PostService postService;
    @Autowired
    private MyUserService userService;
    @GetMapping("/{postId}")
    public String updatePost(@PathVariable long postId, Model model){
        Post post = postService.findPostById(postId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        MyUser user = userService.findByUsername(currentUsername);
        if (post==null || !user.getPosts().contains(post)){
            return "redirect:/";
        }
        else{
            model.addAttribute("post", post);
            model.addAttribute("isAdd", false);
            return "Posting";
        }
    }

    @PostMapping("/submitUpdate/{postId}")
    public String submitUpdatePost(@PathVariable long postId, Post post){
        postService.update(post, postId);
        return "redirect:/profile";
    }

    @PostMapping("/delete/{postId}")
    public String submitDeletePost(@PathVariable long postId){
        String imgName = postService.findPostById(postId).getImg();
        String imagePath = "D:/springprjcts/Cook_cakes/src/main/resources/images/" + imgName;
        try {
            // Удалите файл изображения
            Files.deleteIfExists(Path.of(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            // Обработка ошибки при удалении файла (можно добавить логирование или уведомление)
        }
        postService.delete(postId);
        return "redirect:/profile";
    }
}
