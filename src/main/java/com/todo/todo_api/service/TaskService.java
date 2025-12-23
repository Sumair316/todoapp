// com/todo/todo_api/service/TaskService.java
package com.todo.todo_api.service;

import com.todo.todo_api.dto.TaskCreateRequest;
import com.todo.todo_api.dto.TaskUpdateRequest;
import com.todo.todo_api.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    Task createTaskFromRequest(TaskCreateRequest request);
    Task updateTaskFromRequest(Long id, TaskUpdateRequest request);
    void deleteTask(Long id);
    Task getTaskById(Long id);

    // ✅ Get ALL tasks (unpaginated)
    List<Task> getAllTasks();

    // ✅ Get tasks with pagination & sorting
    Page<Task> getTasks(Pageable pageable);
}