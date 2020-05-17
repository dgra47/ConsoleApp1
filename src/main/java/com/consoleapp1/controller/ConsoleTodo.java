package com.consoleapp1.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.consoleapp1.repository.TaskRepository;
import com.consoleapp1.service.EmbeddedDbService;
import com.consoleapp1.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleTodo {

    public static void main(String[] args) {
        try (Connection connection = EmbeddedDbService.getConnection()) {
            EmbeddedDbService.createIfNotExists(connection);

            TaskRepository taskRepository = new TaskRepository(connection);
            Scanner scanner = new Scanner(System.in);

            log.info("What would you like do to ? Please, please type on of the followed: " + "\nadd task"
                    + "\nget task" + "\nget all tasks" + "\nedit task" + "\ndelete task" + "\n");
            String action = scanner.nextLine();

            switch (action) {
                case "add task": {
                    TaskService.addTask(taskRepository, scanner);
                    break;

                }
                case "get task": {
                    log.info("Task id : ");
                    int id = scanner.nextInt();
                    taskRepository.getTask(id);
                    break;
                }

                case "get all tasks": {
                    taskRepository.getAllTasks();
                    log.info("All tasks: {}", taskRepository.getAllTasks());
                    break;
                }

                case "edit task": {
                    TaskService.editTask(taskRepository, scanner);
                    log.info("All tasks: {}", taskRepository.getAllTasks());
                    break;
                }

                case "delete task": {
                    log.info("Task id : ");
                    int id = scanner.nextInt();
                    taskRepository.deleteTask(id);
                    break;
                }
            }
        } catch (SQLException e) {
            log.error("Cannot establish db connection", e);
        } catch (IOException e) {
            log.error("Cannot read file for db connection init", e);
        }
    }
}
