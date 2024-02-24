package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl implements MyUserService{

    @Autowired
    private MyUserRepository userRepository;
    @Override
    public void save(MyUser user) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public MyUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
