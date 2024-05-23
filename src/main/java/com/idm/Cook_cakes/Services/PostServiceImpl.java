package com.idm.Cook_cakes.Services;

import com.idm.Cook_cakes.Models.MyUser;
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
    public void delete(long postId) {
        postRepository.deleteById(postId);
    }
    @Override
    public void update(Post newPost, long id) {
        Post updatablePost = postRepository.findById(id);

        if (updatablePost!=null){
            updatablePost.setTitle(newPost.getTitle());
            updatablePost.setDescription(newPost.getDescription());
            updatablePost.setServings(newPost.getServings());
            updatablePost.setPrepTime(newPost.getPrepTime());
            updatablePost.setCookTime(newPost.getCookTime());
            updatablePost.setIngredients(newPost.getIngredients());
            updatablePost.setVariation(newPost.getVariation());
            updatablePost.setTheProcess(newPost.getTheProcess());
            postRepository.save(updatablePost);
        }
    }
    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByIdDesc();
    }

    @Override
    public List<Post> findAllPopularPosts() {
        List<Post> posts = postRepository.findAllByOrderByIdDesc();
        if (posts.size()>6)
            return posts.subList(0,6);
        else
            return posts;
    }

    @Override
    public List<Post> findAllPostsByAuthor(MyUser user) {
        return postRepository.findAllByUser(user);
    }

    @Override
    public Post findPostById(long id) {
        return postRepository.findById(id);
    }


}
