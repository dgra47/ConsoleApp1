package com.consoleapp1.service;

import java.util.Scanner;

import com.consoleapp1.model.Task;
import com.consoleapp1.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskService {
    public static void addTask(TaskRepository taskRepository, Scanner scanner) {
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

        taskRepository.addTask(task);
    }

    public static void editTask(TaskRepository taskRepository, Scanner scanner) {
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

        taskRepository.editTask(task);
    }
}
