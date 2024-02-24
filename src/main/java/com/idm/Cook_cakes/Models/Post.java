package com.idm.Cook_cakes.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
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
    String youtubeURL;
    List<String> img;
    int likes;
    Timestamp date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    MyUser user;
}
