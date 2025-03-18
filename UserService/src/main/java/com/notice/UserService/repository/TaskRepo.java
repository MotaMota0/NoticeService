package com.notice.UserService.repository;

import com.notice.UserService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {

    List<Task> findByAuthorId(Long authorId);
    List<Task> findByAssigneeId(Long assigneeId);


}
