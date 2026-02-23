package com.journalsphere.backend.graphql.mutation;

import com.journalsphere.backend.model.Post;
import com.journalsphere.backend.model.User;
import com.journalsphere.backend.security.CustomUserDetails;
import com.journalsphere.backend.service.PostService;
import com.journalsphere.backend.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class PostMutationResolver {
    private final PostService postService;
    private final UserService userService;

    public PostMutationResolver(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }

    @MutationMapping
    public Post createPost(
            @Argument String title,
            @Argument String content,
            @AuthenticationPrincipal CustomUserDetails userDetails
            ){

        // Extract authentication from JWT
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthorized");
        }

        String username = userDetails.getUsername();

        User author = userService
                .findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);

        return postService.save(post);
    }
}
