package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Services.MyUserService;
import com.idm.Cook_cakes.Services.MyUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class RegisterController {
    @Autowired
    private MyUserService userService;
    @Autowired
    private MyUserDetailsServiceImpl userDetailsService;

    @GetMapping("")
    public String auth(Model model){
        model.addAttribute("user", new MyUser());
        model.addAttribute("newuser", new MyUser());
        model.addAttribute("passwordAnother", "");
        return "logIn";
    }

    @PostMapping("/submitreg")
    public String reg(@ModelAttribute("newuser") MyUser user,
                      @RequestParam(name = "passwordAnother", required =false) String passwordAnother,
                      Model model){
        if (!user.getPassword().equals(passwordAnother)){

            return "redirect:/auth";
        }
        else if (userService.findByNickname((user.getNickname()))!=null || userService.findByUsername((user.getUsername()))!=null){

            return "redirect:/auth";
        }
        else{
            userService.save(user);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
            return "redirect:/";
        }
    }

}
