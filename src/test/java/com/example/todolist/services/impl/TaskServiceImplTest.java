package com.example.todolist.services.impl;

import com.example.todolist.entities.Task;
import com.example.todolist.enums.TaskStatusEnum;
import com.example.todolist.exceptions.BadRequestException;
import com.example.todolist.mappers.TaskMapper;
import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;
import com.example.todolist.models.crud.UpdateTaskModel;
import com.example.todolist.repositories.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper taskMapper;

    @Test
    void getAll_shouldReturnAllTaskModels() {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Description");
        task.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        TaskModel taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setDescription("Description");
        taskModel.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        when(taskRepository.findAll())
                .thenReturn(Collections.singletonList(task));
        when(taskMapper.toModel(task))
                .thenReturn(taskModel);

        List<TaskModel> responses = taskService.getAll();

        assertEquals(1, responses.size());
        verify(taskRepository)
                .findAll();
    }

    @Test
    void getById_withValidId_shouldReturnTaskModelWithId() {
        Task task = new Task();
        task.setId(1L);
        task.setDescription("Description");
        task.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        TaskModel taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setDescription("Description");
        taskModel.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));
        when(taskMapper.toModel(task))
                .thenReturn(taskModel);

        TaskModel response = taskService.getById(1L);
        assertEquals(taskModel, response);
    }

    @Test
    void getById_withValidId_shouldThrowBadRequestException() {
        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> taskService.getById(1L));
    }

    @Test
    void create_withValidCreateTaskModel_shouldReturnTaskModel() {
        CreateTaskModel createTaskModel = new CreateTaskModel();
        createTaskModel.setDescription("Description");

        Task task = new Task();
        task.setId(1L);
        task.setDescription("Description");
        task.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        TaskModel taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setDescription("Description");
        taskModel.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        when(taskMapper.toEntity(createTaskModel))
                .thenReturn(task);
        when(taskRepository.save(task))
                .thenReturn(task);
        when(taskMapper.toModel(task))
                .thenReturn(taskModel);

        TaskModel response = taskService.create(createTaskModel);
        assertEquals(taskModel, response);
    }

    @Test
    void update_withValidIdAndUpdateTaskModel_shouldReturnTaskModel() {
        UpdateTaskModel updateTaskModel = new UpdateTaskModel();
        updateTaskModel.setDescription("Description");

        Task task = new Task();
        task.setId(1L);
        task.setDescription("Description");
        task.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        TaskModel taskModel = new TaskModel();
        taskModel.setId(1L);
        taskModel.setDescription("Description");
        taskModel.setTaskStatusEnum(TaskStatusEnum.NOT_FINISHED);

        when(taskRepository.findById(1L))
                .thenReturn(Optional.of(task));
        when(taskRepository.save(task))
                .thenReturn(task);
        when(taskMapper.toModel(task))
                .thenReturn(taskModel);

        TaskModel response = taskService.update(1L, updateTaskModel);
        assertEquals(taskModel, response);
    }

    @Test
    void update_withValidIdAndUpdateTaskModel_shouldThrowBadRequestException() {
        UpdateTaskModel updateTaskModel = new UpdateTaskModel();
        updateTaskModel.setDescription("Description");

        when(taskRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> taskService.update(1L, updateTaskModel));
    }

    @Test
    void deleteById_withValidId_shouldReturnVoid() {
        doNothing()
                .when(taskRepository)
                .deleteById(1L);

        taskService.deleteById(1L);
        verify(taskRepository)
                .deleteById(1L);
    }
}