package com.app.services.impl;

import com.app.exceptions.ResourceNotFoundException;
import com.app.mappers.TaskMapper;
import com.app.models.dtos.TaskDTO;
import com.app.models.entities.Task;
import com.app.models.entities.User;
import com.app.models.requests.CreateTaskRequest;
import com.app.repositories.ITaskRepository;
import com.app.repositories.IUserRepository;
import com.app.services.ITaskService;
import com.app.utils.enums.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private final IUserRepository userRepository;
    private final ITaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDTO createTask(CreateTaskRequest task) {
        User user = getAuthenticatedUser();

        Task taskCreated = Task
                .builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .status(TaskStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .completedAt(null)
                .user(user)
                .build();

        return taskMapper.toTaskDTO(taskRepository.save(taskCreated));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        User user = getAuthenticatedUser();

        return taskRepository.findAllByUser(user)
                .stream()
                .map(taskMapper::toTaskDTO)
                .toList();
    }

    @Override
    public TaskDTO getTaskById(Long id) {
        User user = getAuthenticatedUser();
        return taskMapper.toTaskDTO(taskRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found.")));
    }

    @Override
    public TaskDTO updateTask(Long id, CreateTaskRequest task) {
        User user = getAuthenticatedUser();
        Task taskEntity = taskRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found."));

        taskEntity.setTitle(task.getTitle());
        taskEntity.setDescription(task.getDescription());

        return taskMapper.toTaskDTO(taskRepository.save(taskEntity));
    }

    @Override
    public TaskDTO completeTask(Long id) {
        User user = getAuthenticatedUser();
        Task taskEntity = taskRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found."));

        taskEntity.setStatus(TaskStatus.COMPLETED);
        taskEntity.setCompletedAt(LocalDateTime.now());

        return taskMapper.toTaskDTO(taskRepository.save(taskEntity));
    }

    @Override
    public void deleteTask(Long id) {
        User user = getAuthenticatedUser();
        Task taskEntity = taskRepository.findByIdAndUser(id, user).orElseThrow(() -> new ResourceNotFoundException("Task with id: " + id + " not found."));
        taskRepository.delete(taskEntity);
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with email: " + email + " not found."));
    }
}
