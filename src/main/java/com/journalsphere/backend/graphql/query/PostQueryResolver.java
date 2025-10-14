package com.journalsphere.backend.graphql.query;

import com.journalsphere.backend.model.Post;
import com.journalsphere.backend.service.PostService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PostQueryResolver {
    private final PostService postService;

    public PostQueryResolver(PostService postService){
        this.postService = postService;
    }

    @QueryMapping
    public List<Post> posts(){
        return postService.getAll();
    }

    @QueryMapping
    public Post post(@Argument String id){
        return postService.getById(Long.parseLong(id)).orElse(null);
    }
}
