package com.consoleapp1.repository;

import java.util.List;

import com.consoleapp1.model.Task;

public interface TaskRepositoryInterface {

    Task getTask(int id);

    List<Task> getAllTasks();

    Task addTask(Task task);

    boolean deleteTask(int id);

    Task editTask(Task task);

}

