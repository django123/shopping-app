package com.django.it.shoppingapp.web;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.TaskRequest;
import com.django.it.shoppingapp.dtos.responses.TaskResponse;
import com.django.it.shoppingapp.services.TaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tasks")
@Tag(name = "Task")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TaskResource {

    private final TaskService taskService;

    @PostMapping("/create/{shop-id}/task")
    public ResponseEntity<String> saveTask(@Valid @RequestBody final TaskRequest taskRequest, @PathVariable("shop-id") Integer shopId) {
        taskService.saveTask(taskRequest, shopId);
        return ResponseEntity.ok("Task created successfully");
    }

    @GetMapping
    public ResponseEntity<PageResponse<TaskResponse>> getAllTasks(

            @RequestParam(name = "page", defaultValue = "0",required = false) int page,
            @RequestParam(name = "size", defaultValue = "10",required = false) int size
    ) {
        return ResponseEntity.ok(taskService.findAllTasks(page, size));
    }

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable("task-id") Integer id) {
        return ResponseEntity.ok(taskService.findTaskById(id));
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<String> deleteTaskById(@PathVariable("task-id") Integer id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<String> updateTaskById(@PathVariable("task-id") Integer id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(taskRequest,id);
        return ResponseEntity.ok("Task updated successfully");
    }

    @GetMapping("/active/{task-id}")
    public ResponseEntity<String> activedTaskById(@PathVariable("task-id") Integer id) {
        taskService.activatedTask(id);
        return ResponseEntity.ok("Task activated successfully");
    }
}
