// src/main/java/com/todo/todo_api/controller/TaskController.java
package com.todo.todo_api.controller;

import com.todo.todo_api.dto.*;
import com.todo.todo_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskCreateRequest request) {
        var task = service.createTaskFromRequest(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(task));
    }

    // ✅ Endpoint 1: Get ALL tasks (unpaginated)
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<com.todo.todo_api.entity.Task> tasks = service.getAllTasks();
        List<TaskResponse> responses = tasks.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // ✅ Endpoint 2: Get PAGINATED tasks
    @GetMapping("/paginated")
    public ResponseEntity<TaskPageResponse> getPaginatedTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDir) {

        // Validate sort direction
        Sort.Direction direction = sortDir.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Only allow safe sort fields
        if (!sortBy.equals("id") && !sortBy.equals("title") && !sortBy.equals("completed")) {
            sortBy = "id";
        }

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortBy);
        Page<com.todo.todo_api.entity.Task> taskPage = service.getTasks(pageRequest);

        List<TaskResponse> content = taskPage.getContent().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        TaskPageResponse response = new TaskPageResponse(
                content,
                taskPage.getNumber(),
                taskPage.getSize(),
                taskPage.getTotalElements(),
                taskPage.getTotalPages(),
                taskPage.isFirst(),
                taskPage.isLast()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        var task = service.getTaskById(id);
        return ResponseEntity.ok(toResponse(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest request) {
        var task = service.updateTaskFromRequest(id, request);
        return ResponseEntity.ok(toResponse(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        service.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private TaskResponse toResponse(com.todo.todo_api.entity.Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.isCompleted()
        );
    }
}