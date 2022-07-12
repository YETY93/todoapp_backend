package com.yety.todoapp.mapper;

import com.yety.todoapp.DTO.TaskInDTO;
import com.yety.todoapp.persistence.entity.Task;
import com.yety.todoapp.persistence.entity.TaskStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOtoTask implements IMapper <TaskInDTO, Task> {


    @Override
    public Task map(TaskInDTO in) {
        Task task = new Task();
        task.setTitle(in.getTitle());
        task.setDescription(in.getDescription());
        task.setEta(in.getEta());
        task.setCreateDate(LocalDateTime.now());
        task.setFinisished(false);
        task.setTaskStatus(TaskStatus.ON_TINE);

        return task;
    }
}
