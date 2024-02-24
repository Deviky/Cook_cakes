package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.MyUser;

public interface MyUserService{
    void save(MyUser user);
    MyUser findByNickname(String nickname);
    MyUser findByUsername(String username);
}
