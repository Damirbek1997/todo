package com.example.todolist.services;

import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;
import com.example.todolist.models.crud.UpdateTaskModel;

import java.util.List;

public interface TaskService {
    List<TaskModel> getAll();
    TaskModel getById(Long id);
    TaskModel create(CreateTaskModel createTaskModel);
    TaskModel update(Long id, UpdateTaskModel updateTaskModel);
    void deleteById(Long id);
}
