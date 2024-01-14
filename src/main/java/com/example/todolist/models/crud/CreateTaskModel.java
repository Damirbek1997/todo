package com.example.todolist.models.crud;

import com.example.todolist.enums.TaskStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskModel {
    @NotNull(message = "Description must be filled!")
    private String description;
    private TaskStatusEnum taskStatusEnum;
}
