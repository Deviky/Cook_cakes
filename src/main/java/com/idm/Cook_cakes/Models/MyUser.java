package com.idm.Cook_cakes.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String nickname;
    String username;
    String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

}
