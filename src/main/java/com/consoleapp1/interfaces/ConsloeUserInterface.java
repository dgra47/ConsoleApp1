package com.consoleapp1.interfaces;

import java.sql.Connection;
import java.util.Scanner;

import com.consoleapp1.model.Task;
import com.consoleapp1.repository.TaskRepository;
import com.consoleapp1.repository.TaskRepositoryInterface;
import com.consoleapp1.service.TaskService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ConsloeUserInterface {
    private final Connection connection;

    public void execute() {
        TaskRepositoryInterface taskRepository = new TaskRepository(connection);
        TaskService taskService = new TaskService(taskRepository);
        Scanner scanner = new Scanner(System.in);

        log.info("What would you like do to ? Please, please type on of the followed: " + "\nadd task"
                + "\nget task" + "\nget all tasks" + "\nedit task" + "\ndelete task" + "\n");
        String action = scanner.nextLine();

        switch (action) {
            case "add task": {
                Task task = getTask(scanner);
                taskService.addTask(task);
                break;

            }
            case "get task": {
                log.info("Task id : ");
                int id = scanner.nextInt();
                Task task = taskService.getTask(id);
                log.info("Getted tasks: {}", task);
                break;
            }

            case "get all tasks": {
                taskService.getAllTasks();
                log.info("All tasks: {}", taskRepository.getAllTasks());
                break;
            }

            case "edit task": {
                Task task = getTaskForEdit(taskRepository, scanner);
                taskService.editTask(task);
                log.info("All tasks: {}", taskRepository.getAllTasks());
                break;
            }

            case "delete task": {
                log.info("Task id : ");
                int id = scanner.nextInt();
                taskService.deleteTask(id);
                break;
            }
        }
    }

    private Task getTask(Scanner scanner) {
        Task task = new Task();
        log.info("Task id : ");
        task.setId(scanner.nextInt());
        scanner.nextLine();

        log.info("Name of task: ");
        task.setName(scanner.nextLine());

        log.info("Responsibility of: ");
        task.setAssigned(scanner.nextLine());

        log.info("Priority 1-5: ");
        task.setPriority(scanner.nextInt());
        scanner.nextLine();

        log.info("Description: ");
        task.setDescription(scanner.nextLine());

        log.info("Completed (TRUE/FALSE) : ");
        task.setCompleted(scanner.nextBoolean());
        scanner.nextLine();

        return task;
    }

    private Task getTaskForEdit(TaskRepositoryInterface taskRepository, Scanner scanner) {

        log.info("Type id in order to edit task by id : ");
        String idStr = scanner.nextLine();
        int id = Integer.parseInt(idStr);
        Task task = taskRepository.getTask(id);

        log.info("Name of task: ");
        task.setName(scanner.nextLine());

        log.info("Responsibility of: ");
        task.setAssigned(scanner.nextLine());

        log.info("Priority 1-5: ");
        task.setPriority(scanner.nextInt());
        scanner.nextLine();

        log.info("Description: ");
        task.setDescription(scanner.nextLine());

        log.info("Completed (TRUE/FALSE) : ");
        task.setCompleted(scanner.nextBoolean());
        scanner.nextLine();

        return task;
    }
}
