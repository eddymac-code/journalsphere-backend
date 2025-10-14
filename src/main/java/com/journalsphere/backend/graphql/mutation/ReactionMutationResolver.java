package com.journalsphere.backend.graphql.mutation;

import com.journalsphere.backend.model.Reaction;
import com.journalsphere.backend.service.ReactionService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReactionMutationResolver {
    private final ReactionService reactionService;

    public ReactionMutationResolver(
            ReactionService reactionService
    ){
        this.reactionService = reactionService;
    }

    @MutationMapping
    public Reaction reactToPost(
            @Argument String postId,
            @Argument String userId,
            @Argument String type
    ){
        return reactionService.addReaction(Long.parseLong(postId), Long.parseLong(userId), type);
    }
}
