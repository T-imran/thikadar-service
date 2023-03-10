package com.example.task.service;

import com.example.task.model.TaskModel;
import com.example.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    public final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Get All Tasks
     */
    public List<TaskModel> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Save task to db.
     */
    public TaskModel save(TaskModel project) {
        return taskRepository.save(project);
    }

    /**
     * Find a task by id.
     */
    public TaskModel getById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /**
     * Delete a task by id.
     */
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}


