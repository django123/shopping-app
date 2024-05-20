package com.django.it.shoppingapp.mapper;

import com.django.it.shoppingapp.dtos.requests.TaskRequest;
import com.django.it.shoppingapp.dtos.responses.TaskResponse;
import com.django.it.shoppingapp.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {

    public Task mapToTask(final TaskRequest request) {
        return  Task.builder()
                .name(request.name())
                .description(request.description())
                .status(request.status())
                .build();
    }

    public TaskResponse mapToTaskResponse(final Task task) {
        return TaskResponse.builder()
                .description(task.getDescription())
                .name(task.getName())
                .status(task.isStatus())
                .build();
    }

}
