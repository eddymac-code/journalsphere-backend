package com.journalsphere.backend.graphql.mutation;

import com.journalsphere.backend.model.Comment;
import com.journalsphere.backend.service.CommentService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class CommentMutationResolver {
    private final CommentService commentService;

    public CommentMutationResolver(
            CommentService commentService
    ){
        this.commentService = commentService;
    }

    @MutationMapping
    public Comment createComment(
            @Argument String content,
            @Argument String postId,
            @Argument String authorId
    ){

        return commentService.addComment(Long.parseLong(postId), Long.parseLong(authorId), content);
    }
}
