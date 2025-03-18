package com.notice.UserService.service;

import com.notice.UserService.model.Comment;
import com.notice.UserService.model.Task;
import com.notice.UserService.model.User;
import com.notice.UserService.repository.CommentRepo;
import com.notice.UserService.repository.TaskRepo;
import com.notice.UserService.repository.UserRepo;

import java.util.List;

public class CommentService {

    private final CommentRepo commentRepository;
    private final TaskRepo taskRepository;
    private final UserRepo userRepository;

    public CommentService(CommentRepo commentRepository,
                          TaskRepo taskRepository,
                          UserRepo userRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public Comment addComment(Long taskId, Long userId, String text) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setTask(task);
        comment.setText(text);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }
}
