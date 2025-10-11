package com.journalsphere.backend.service;

import com.journalsphere.backend.model.Post;
import com.journalsphere.backend.model.Reaction;
import com.journalsphere.backend.model.User;
import com.journalsphere.backend.repository.PostRepository;
import com.journalsphere.backend.repository.ReactionRepository;
import com.journalsphere.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public ReactionService(
            ReactionRepository reactionRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ){
        this.reactionRepository = reactionRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Reaction addReaction(Long postId, Long userId, String type){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        Reaction r = new Reaction();
        r.setPost(post);
        r.setUser(user);
        r.setType(type);

        return reactionRepository.save(r);
    }

    public List<Reaction> getReactions(){
        return reactionRepository.findAll();
    }

    public Optional<Reaction> getById(Long id){
        return reactionRepository.findById(id);
    }

    public void delete(Long id){
        reactionRepository.deleteById(id);
    }
}
