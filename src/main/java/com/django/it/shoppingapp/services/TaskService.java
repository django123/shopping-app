package com.django.it.shoppingapp.services;

import com.django.it.shoppingapp.common.PageResponse;
import com.django.it.shoppingapp.dtos.requests.TaskRequest;
import com.django.it.shoppingapp.dtos.responses.TaskResponse;


public interface TaskService {

    PageResponse<TaskResponse> findAllTasks(int page, int size);

    Integer saveTask(TaskRequest taskRequest, Integer shopId);
    TaskResponse findTaskById(Integer id);
    void deleteTaskById(Integer id);
    void updateTask(TaskRequest taskRequest, Integer id);

    boolean activatedTask(Integer id);
}
