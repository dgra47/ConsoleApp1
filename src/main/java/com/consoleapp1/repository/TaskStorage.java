package com.consoleapp1.repository;

import com.consoleapp1.model.Task;
import java.util.List;

public interface TaskStorage {

    Task getTask(int id);

    List<Task> getAllTasks();

    Task addTask(Task task);

    boolean deleteTask(int id);

}

