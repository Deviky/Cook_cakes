package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.MyUser;
import com.idm.Cook_cakes.Models.Post;

import java.util.List;

public interface PostService {
    void save(Post post);

    void delete(long postId);

    void update(Post newPost, long id);

    List<Post> findAllPosts();
    List<Post> findAllPopularPosts();
    List<Post> findAllPostsByAuthor(MyUser user);
    Post findPostById(long id);
}
