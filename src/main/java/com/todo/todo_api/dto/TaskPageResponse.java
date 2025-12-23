// com/todo/todo_api/dto/TaskPageResponse.java
package com.todo.todo_api.dto;

import java.util.List;

public class TaskPageResponse {
    private List<TaskResponse> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;

    public TaskPageResponse(List<TaskResponse> content, int pageNumber, int pageSize,
                            long totalElements, int totalPages, boolean first, boolean last) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.first = first;
        this.last = last;
    }

    // Getters
    public List<TaskResponse> getContent() { return content; }
    public int getPageNumber() { return pageNumber; }
    public int getPageSize() { return pageSize; }
    public long getTotalElements() { return totalElements; }
    public int getTotalPages() { return totalPages; }
    public boolean isFirst() { return first; }
    public boolean isLast() { return last; }
}