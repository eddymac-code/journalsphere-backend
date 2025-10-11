package com.journalsphere.backend.service;

import com.journalsphere.backend.model.Comment;
import com.journalsphere.backend.model.Post;
import com.journalsphere.backend.model.User;
import com.journalsphere.backend.repository.CommentRepository;
import com.journalsphere.backend.repository.PostRepository;
import com.journalsphere.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public  CommentService(
            CommentRepository commentRepository,
            PostRepository postRepository,
            UserRepository userRepository
    ){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long postId, Long authorId, String content){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + postId));
        User author = userRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found: " + authorId));

        Comment c = new Comment();
        c.setPost(post);
        c.setAuthor(author);
        c.setContent(content);

        return commentRepository.save(c);
    }

    public List<Comment> getPostComments(Long id){
        return commentRepository.findByPostId(id);
    }

    public Optional<Comment> getById(Long id){
        return commentRepository.findById(id);
    }

    public void delete(Long id){
        commentRepository.deleteById(id);
    }
}
