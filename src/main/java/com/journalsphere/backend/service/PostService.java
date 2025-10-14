package com.journalsphere.backend.service;

import com.journalsphere.backend.model.Post;
import com.journalsphere.backend.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // create or update
    public Post save(Post post) {
        return postRepository.save(post);
    }

    // get all posts
    public List<Post> getAll(){
        return postRepository.findAll();
    }

    // read single post by id
    public Optional<Post> getById(Long id){
        return postRepository.findById(id);
    }

    // get posts by author id
    public List<Post> getByAuthorId(Long authorId){
        return postRepository.findByAuthorId(authorId);
    }

    // delete
    public void delete(Long id){
        postRepository.deleteById(id);
    }
}
