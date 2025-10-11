package com.journalsphere.backend.repository;

import com.journalsphere.backend.model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {

    List<Reaction> findByPostId(Long postId);
}
