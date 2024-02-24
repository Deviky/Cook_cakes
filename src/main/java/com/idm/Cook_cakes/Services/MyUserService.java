package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Repositories.MyUserRepository;

public interface MyUserService{
    void save(MyUser user);
    MyUser findByUsername(String username);
}
