package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.Post;

import java.util.List;

public interface PostService {
    void save(Post post);
    List<Post> findAllPosts();
    List<Post> findAllPopularPosts();
}
