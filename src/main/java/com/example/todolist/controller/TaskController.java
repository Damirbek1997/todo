package com.example.todolist.controller;

import com.example.todolist.models.TaskModel;
import com.example.todolist.models.crud.CreateTaskModel;
import com.example.todolist.models.crud.UpdateTaskModel;
import com.example.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all tasks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all tasks"),
            @ApiResponse(responseCode = "400", description = "The request is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request", content = @Content),
    })
    public List<TaskModel> getAll() {
        return taskService.getAll();
    }

    @GetMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved task by id"),
            @ApiResponse(responseCode = "400", description = "The request is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request", content = @Content),
    })
    public TaskModel getById(@PathVariable Long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a task"),
            @ApiResponse(responseCode = "400", description = "The request is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request", content = @Content),
    })
    public TaskModel create(@RequestBody @Valid CreateTaskModel createTaskModel) {
        return taskService.create(createTaskModel);
    }

    @PutMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated a task"),
            @ApiResponse(responseCode = "400", description = "The request is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request", content = @Content),
    })
    public TaskModel update(@PathVariable Long id, @RequestBody UpdateTaskModel updateTaskModel) {
        return taskService.update(id, updateTaskModel);
    }

    @DeleteMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete task by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted task by id"),
            @ApiResponse(responseCode = "400", description = "The request is incorrect", content = @Content),
            @ApiResponse(responseCode = "404", description = "The resource you were trying to reach is not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Application failed to process the request", content = @Content),
    })
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
