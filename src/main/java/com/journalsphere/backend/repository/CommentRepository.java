package com.journalsphere.backend.repository;

import com.journalsphere.backend.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostId(Long id);
    public List<Comment> findByAuthorId(Long id);
}
