package com.consoleapp1.service;

import java.util.List;

import com.consoleapp1.model.Task;
import com.consoleapp1.repository.TaskRepositoryInterface;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskService {
    private final TaskRepositoryInterface taskRepository;

    public Task getTask(int id) {
        return taskRepository.getTask(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Task addTask(Task task) {
        return taskRepository.addTask(task);
    }

    public boolean deleteTask(int id) {
        return taskRepository.deleteTask(id);
    }

    public Task editTask(Task task) {
        return taskRepository.editTask(task);
    }

}
