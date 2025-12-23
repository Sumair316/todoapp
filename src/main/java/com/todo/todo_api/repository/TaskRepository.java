// com/todo/todo_api/repository/TaskRepository.java
package com.todo.todo_api.repository;

import com.todo.todo_api.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAll(Pageable pageable);
}