package com.idm.Cook_cakes.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String username;
    String email;
    String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Post> posts;

}
