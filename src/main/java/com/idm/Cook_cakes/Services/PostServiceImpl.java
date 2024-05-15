package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.Post;
import com.idm.Cook_cakes.Repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Post> findAllPopularPosts() {
        List<Post> posts = postRepository.findTop6ByOrderByLikesDesc();
        if (posts.size()>6)
            return posts.subList(0,6);
        else
            return posts;
    }
}
