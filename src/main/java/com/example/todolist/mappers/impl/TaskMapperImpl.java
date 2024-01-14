package com.example.todolist.mappers.impl;

import com.example.todolist.entities.Task;
import com.example.todolist.mappers.TaskMapper;
import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskModel toModel(Task task) {
        return TaskModel.builder()
                .id(task.getId())
                .description(task.getDescription())
                .taskStatusEnum(task.getTaskStatusEnum())
                .build();
    }

    @Override
    public Task toEntity(CreateTaskModel createTaskModel) {
        Task task = new Task();
        task.setDescription(createTaskModel.getDescription());

        if (createTaskModel.getTaskStatusEnum() != null) {
            task.setTaskStatusEnum(createTaskModel.getTaskStatusEnum());
        }

        return task;
    }
}
