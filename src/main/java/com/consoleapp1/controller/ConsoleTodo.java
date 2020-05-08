package com.consoleapp1.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.consoleapp1.repository.TaskRepository;
import com.consoleapp1.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsoleTodo  {

    public static void main(String[] args) {
        try (Connection connection = getConnectionOrExit()) {

            TaskRepository taskRepository = new TaskRepository(connection);
            Scanner scanner = new Scanner(System.in);

            log.info("What would you like do to: add task, get task, get all tasks, delete task?");
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

                case "delete task": {
                    log.info("Task id : ");
                    int id = scanner.nextInt();
                    taskRepository.deleteTask(id);
                    break;
                }

            }
        } catch (SQLException e) {
            log.error("Cannot establish db connection", e);
        }
    }

    private static Connection getConnectionOrExit() throws SQLException {
        Connection connection;

        // exit if it is not possible load driver
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch(ClassNotFoundException ex) {
            log.error("Unable to load class", ex);
            System.exit(1);
        }

        // get connection after driver load
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:8080/tasksdb",
                "postgres",
                "postgres");

        return connection;
    }

}
