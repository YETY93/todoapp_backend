package com.yety.todoapp.persistence.entity.respository;

import com.yety.todoapp.persistence.entity.Task;
import com.yety.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Long> {
    /**
     *
     * @param taskStatus
     * @return una lista de tareas segun el estado de la tarea
     */
    public List<Task> findAllByTaskStatus(TaskStatus taskStatus);

    /**
     *
     * @param id
     */
    @Modifying
    @Query(value = "UPDATE todo_app_db.task SET finisished = TRUE WHERE task.id = :id ", nativeQuery = true)
    public void markTaskASFinished(@Param("id") Long id);


}
