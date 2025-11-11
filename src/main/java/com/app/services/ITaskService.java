package com.app.services;

import com.app.models.dtos.TaskDTO;
import com.app.models.requests.CreateTaskRequest;

import java.util.List;

public interface ITaskService {
    public TaskDTO createTask(CreateTaskRequest task);
    public List<TaskDTO> getAllTasks();
    public TaskDTO getTaskById(Long id);
    public TaskDTO updateTask(Long id, CreateTaskRequest task);
    public TaskDTO completeTask(Long id);
    public void deleteTask(Long id);
}
