package com.idm.Cook_cakes.Controllers;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Services.MyUserService;
import com.idm.Cook_cakes.Services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private MyUserService userService;
    @PostMapping("/auth/submitlog")
    public String login(@ModelAttribute("user") MyUser user, Model model) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if (userDetails != null) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities())
            );
            return "redirect:/profile";
        } else {
            return "redirect:/auth";
        }
   }
}
