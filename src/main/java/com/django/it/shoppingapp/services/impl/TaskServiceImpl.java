package com.django.it.shoppingapp.services.impl;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.TaskRequest;
import com.django.it.shoppingapp.dtos.responses.TaskResponse;
import com.django.it.shoppingapp.mapper.TaskMapper;
import com.django.it.shoppingapp.model.Task;
import com.django.it.shoppingapp.repositories.ShoppingRepository;
import com.django.it.shoppingapp.repositories.TaskRepository;
import com.django.it.shoppingapp.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ShoppingRepository shoppingRepository;
    private final TaskMapper taskMapper;


    @Override
    public PageResponse<TaskResponse> findAllTasks(int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Task> tasks = taskRepository.findAll(pageable);
        List<TaskResponse> taskResponses = tasks.stream()
                .map(taskMapper::mapToTaskResponse)
                .toList();
        return new PageResponse<>(
                taskResponses,
                tasks.getNumber(),
                tasks.getSize(),
                tasks.getTotalPages(),
                (int) tasks.getTotalElements(),
                tasks.isFirst(),
                tasks.isLast()
        );
    }

    @Override
    public Integer saveTask(TaskRequest taskRequest, Integer shopId) {
        Task task = taskMapper.mapToTask(taskRequest);
       return   shoppingRepository.findById(shopId).map(s -> {
             task.setStatus(false);
             task.setShopping(s);
             return taskRepository.save(task).getId();
         }).orElseThrow(() -> new EntityNotFoundException("Not shopping found with id : " + shopId));

    }

    @Override
    public TaskResponse findTaskById(Integer id) {
        return taskRepository.findById(id)
                .map(taskMapper::mapToTaskResponse)
                .orElseThrow(() -> new EntityNotFoundException("Not Task found with id: " + id));
    }

    @Override
    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void updateTask(TaskRequest taskRequest, Integer id) {
       final Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Task found with id: " + id));
       taskMapper.mapToTask(taskRequest);
       taskRepository.save(task);
    }

    @Override
    public boolean activatedTask(Integer id) {

        Task task = taskRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Task found with id: " + id));
        if (task.isStatus()){
            task.setStatus(false);
        }else {
            task.setStatus(true);
        }
        taskRepository.save(task);
        return task.isStatus();
    }
}
