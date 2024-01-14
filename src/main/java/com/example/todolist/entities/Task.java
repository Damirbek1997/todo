package com.example.todolist.entities;

import com.example.todolist.enums.TaskStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatusEnum taskStatusEnum = TaskStatusEnum.NOT_FINISHED;
}
