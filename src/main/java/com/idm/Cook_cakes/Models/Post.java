package com.idm.Cook_cakes.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String title;
    String description;
    String img;
    int likes;
    int servings;
    int prepTime;
    int cookTime;
    String ingredients;
    String variation;
    String theProcess;
    @ManyToOne
    @JoinColumn(name = "user_id")
    MyUser user;

    @ManyToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_liked_id")
    )
    private List<MyUser> likedByUsers;

}
