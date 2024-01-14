package com.example.todolist.mappers;

import com.example.todolist.entities.Task;
import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;

public interface TaskMapper {
    TaskModel toModel(Task task);
    Task toEntity(CreateTaskModel createTaskModel);
}
