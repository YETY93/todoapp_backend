package com.yety.todoapp.services;

import com.yety.todoapp.DTO.TaskInDTO;
import com.yety.todoapp.exceptions.ToDoExceptions;
import com.yety.todoapp.mapper.TaskInDTOtoTask;
import com.yety.todoapp.persistence.entity.Task;
import com.yety.todoapp.persistence.entity.TaskStatus;
import com.yety.todoapp.persistence.entity.respository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOtoTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOtoTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask (TaskInDTO taskInDTO){
        Task task = mapper.map(taskInDTO);
        return this.repository.save(task);
    }

    public List<Task> getAll(){
        return this.repository.findAll();
    }

    public List<Task> getByTaskStatus(TaskStatus taskStatus){
        return this.repository.findAllByTaskStatus(taskStatus);
    }
    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskASFinished(id);
    }

    public void deleteTask(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
