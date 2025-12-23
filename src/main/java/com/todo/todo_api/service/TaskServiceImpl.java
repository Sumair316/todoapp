// com/todo/todo_api/service/TaskServiceImpl.java
package com.todo.todo_api.service;

import com.todo.todo_api.dto.TaskCreateRequest;
import com.todo.todo_api.dto.TaskUpdateRequest;
import com.todo.todo_api.entity.Task;
import com.todo.todo_api.exception.ResourceNotFoundException;
import com.todo.todo_api.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTaskFromRequest(TaskCreateRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setCompleted(request.isCompleted());
        return repository.save(task);
    }

    @Override
    public Task updateTaskFromRequest(Long id, TaskUpdateRequest request) {
        Task existing = getTaskById(id);
        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.setCompleted(request.isCompleted());
        return repository.save(existing);
    }

    @Override
    public void deleteTask(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
    }

    // ✅ Get all tasks (no pagination)
    @Override
    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    // ✅ Paginated version
    @Override
    public Page<Task> getTasks(Pageable pageable) {
        return repository.findAll(pageable);
    }
}