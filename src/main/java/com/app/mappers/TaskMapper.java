package com.app.mappers;

import com.app.models.dtos.TaskDTO;
import com.app.models.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO toTaskDTO(Task task) {
        return TaskDTO
                .builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .completedAt(task.getCompletedAt())
                .userId(task.getUser().getId())
                .build();
    }
}
