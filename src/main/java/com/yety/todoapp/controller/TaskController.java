package com.yety.todoapp.controller;

import com.yety.todoapp.DTO.TaskInDTO;
import com.yety.todoapp.persistence.entity.Task;
import com.yety.todoapp.persistence.entity.TaskStatus;
import com.yety.todoapp.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     *
     * @param taskInDTO
     * @return Crea la tarea en la base de datos
     */
    @PostMapping("/create")
    public Task createTask (@RequestBody TaskInDTO taskInDTO){
        return this.taskService.createTask(taskInDTO);
    }

    /**
     *
     * @return un el listado complerto de todas las tareas
     */
    @GetMapping("/get-all")
    public List<Task> getAllTask (){
        return  this.taskService.getAll();
    }

    /**
     *
     * @param taskStatus
     * @return
     */
    @GetMapping("/get-all-status/{status}")
    public List<Task> getTaskStatus(@PathVariable("status") TaskStatus taskStatus){
        return this.taskService.getByTaskStatus(taskStatus);
    }

    /**
     *
     * @param id
     * @return
     */
    @PatchMapping("/mark-finished/{id}")
    public ResponseEntity<Void> markAsFinished (@PathVariable("id") Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete-task/{id}")
    public ResponseEntity<Void> deleteTask (@PathVariable("id") Long id){
        this.taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
