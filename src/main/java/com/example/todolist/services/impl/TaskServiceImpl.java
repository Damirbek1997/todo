package com.example.todolist.services.impl;

import com.example.todolist.entities.Task;
import com.example.todolist.exceptions.BadRequestException;
import com.example.todolist.mappers.TaskMapper;
import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;
import com.example.todolist.models.crud.UpdateTaskModel;
import com.example.todolist.repositories.TaskRepository;
import com.example.todolist.services.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskModel> getAll() {
        List<TaskModel> taskModels = taskRepository.findAll().stream()
                .map(taskMapper::toModel)
                .collect(Collectors.toList());
        log.debug("All tasks retrieved");
        return taskModels;
    }

    @Override
    public TaskModel getById(Long id) {
        TaskModel taskModel = taskMapper.toModel(getEntityById(id));
        log.debug("Task with id {} is retrieved", id);
        return taskModel;
    }

    @Override
    public TaskModel create(CreateTaskModel createTaskModel) {
        Task task = taskMapper.toEntity(createTaskModel);
        task = taskRepository.save(task);
        log.debug("Task - {} is saved", task);
        return taskMapper.toModel(task);
    }

    @Override
    public TaskModel update(Long id, UpdateTaskModel updateTaskModel) {
        Task task = getEntityById(id);
        setValues(task, updateTaskModel);
        task = taskRepository.save(task);
        log.debug("Task - {} is updated", task);
        return taskMapper.toModel(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
        log.debug("Task with id {} is deleted", id);
    }

    private Task getEntityById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new BadRequestException("There is no task with id " + id));
    }

    private void setValues(Task task, UpdateTaskModel updateTaskModel) {
        if (updateTaskModel.getDescription() != null) {
            task.setDescription(updateTaskModel.getDescription());
        }

        if (updateTaskModel.getTaskStatusEnum() != null) {
            task.setTaskStatusEnum(updateTaskModel.getTaskStatusEnum());
        }
    }
}
