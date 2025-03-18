package com.notice.UserService.service;

import com.notice.UserService.model.Task;
import com.notice.UserService.model.User;
import com.notice.UserService.repository.TaskRepo;
import com.notice.UserService.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserRepo userRepo;


    public TaskService(TaskRepo taskRepo, UserRepo userRepo) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
    }

    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        return taskRepo.findById(id);
    }

    public Task createTask(Task task , Long authorId){

            User author = userRepo.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Author not found"));
            task.setAuthor(author);
            return taskRepo.save(task);

    }
    public List<Task> getTasksByAuthor(Long authorId) {
        return taskRepo.findByAuthorId(authorId);
    }

    public List<Task> getTasksByAssignee(Long assigneeId) {
        return taskRepo.findByAssigneeId(assigneeId);
    }

    public void deleteById(Long id){
        taskRepo.deleteById(id);
    }

    public Task updateTask(Long id,Task newTask){
        return taskRepo.findById(id)
                .map(task -> {
                    task.setTitle(newTask.getTitle());
                    task.setDescription(newTask.getDescription());
                    task.setTaskStatus(newTask.getTaskStatus());
                    return taskRepo.save(task);
                }).orElseThrow(() -> new RuntimeException("Task dont find"));
    }
}
