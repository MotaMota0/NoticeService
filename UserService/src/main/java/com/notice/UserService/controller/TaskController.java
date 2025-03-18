package com.notice.UserService.controller;

import com.notice.UserService.model.Task;
import com.notice.UserService.model.User;
import com.notice.UserService.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/getTaskById/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/createTask")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")

    public Task createTask(@RequestBody Task task , User user){
        return taskService.createTask(task,user.getId());
    }
    @GetMapping("/author/{authorId}")
    @PreAuthorize("hasRole('ADMIN') or #authorId == authentication.principal.id")
    public List<Task> getTasksByAuthor(@PathVariable Long authorId) {
        return taskService.getTasksByAuthor(authorId);
    }

    @GetMapping("/assignee/{assigneeId}")
    @PreAuthorize("hasRole('ADMIN') or #assigneeId == authentication.principal.id")
    public List<Task> getTasksByAssignee(@PathVariable Long assigneeId) {
        return taskService.getTasksByAssignee(assigneeId);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
     @PutMapping("/updateById/{id}")
     public ResponseEntity<Task> updateById(@PathVariable Long id,@RequestBody Task task) throws RuntimeException{
        return ResponseEntity.ok(taskService.updateTask(id,task));

     }

}
