package com.example.todolist.models.crud;

import com.example.todolist.enums.TaskStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskModel {
    private String description;
    private TaskStatusEnum taskStatusEnum;
}
