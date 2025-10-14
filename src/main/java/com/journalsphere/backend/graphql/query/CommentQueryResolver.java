package com.journalsphere.backend.graphql.query;

import com.journalsphere.backend.model.Comment;
import com.journalsphere.backend.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CommentQueryResolver {
    private final CommentService commentService;

    public CommentQueryResolver(CommentService commentService){
        this.commentService = commentService;
    }

    @QueryMapping
    public List<Comment> commentsByPost(@Argument String postId){
        return commentService.getPostComments(Long.parseLong(postId));
    }
}
