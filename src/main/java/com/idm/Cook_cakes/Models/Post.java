package com.idm.Cook_cakes.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    int servings;
    int prepTime;
    int cookTime;
    String ingredients;
    String variation;
    String theProcess;
    @ManyToOne
    @JoinColumn(name = "user_id")
    MyUser user;
}
