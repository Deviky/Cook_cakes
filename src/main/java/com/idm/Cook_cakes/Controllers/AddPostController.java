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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/create")
public class AddPostController {
    @Autowired
    private PostService postService;
    @Autowired
    private MyUserService userService;
    @GetMapping("")
    public String addPost(Model model){
        model.addAttribute("post", new Post());
        model.addAttribute("isAdd", true);
        return "posting";
    }

    @PostMapping("/submitCreate")
    public String create(@ModelAttribute("post") Post post, @RequestParam("imageFile") MultipartFile file) throws IOException, InterruptedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String currentUsername = userDetails.getUsername();
        MyUser user = userService.findByUsername(currentUsername);
        post.setUser(user);
        if (!file.isEmpty()) {
            String path = "D:/springprjcts/Cook_cakes/src/main/resources/images";
            Date currentDate = new Date();

            // Форматируем дату и время в строку
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String timestamp = dateFormat.format(currentDate);

            // Генерируем уникальный идентификатор UUID
            String uuidString = UUID.randomUUID().toString().substring(0, 8); // Возьмем первые 8 символов UUID

            // Комбинируем время и UUID для создания уникального имени файла
            String fileName = "post_" + timestamp + "_" + uuidString + ".png";
            File directory = new File(path);

            if (!directory.exists()) {
                directory.mkdirs();  // Create directories if they don't exist
            }

            File saveFile = new File(directory, fileName);
            file.transferTo(saveFile);

            post.setImg(fileName);
        }
        postService.save(post);
        return "redirect:/profile";
    }
}
