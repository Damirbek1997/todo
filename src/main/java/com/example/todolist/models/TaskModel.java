package com.example.todolist.models;

import com.example.todolist.enums.TaskStatusEnum;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private Long id;
    private String description;
    private TaskStatusEnum taskStatusEnum;
}
