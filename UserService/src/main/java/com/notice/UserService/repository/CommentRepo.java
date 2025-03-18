package com.notice.UserService.repository;

import com.notice.UserService.model.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByCommentId(Long commentId);

    List<Comment> findByTaskId(Long id);
}
