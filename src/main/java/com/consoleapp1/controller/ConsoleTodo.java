package com.consoleapp1.controller;
import com.consoleapp1.model.Task;
import com.consoleapp1.repository.TaskStorage1;

import java.util.Scanner;

public class ConsoleTodo {

    public static void main(String[] args) {

        TaskStorage1 taskStorage1 = new TaskStorage1();
        Scanner scanner = new Scanner(System.in);

        System.out.println("What would you like do to: add task, get task, get all tasks, delete task?");
        String action = scanner.nextLine();

        switch (action) {
            case "add task": {
                Task task = new Task();
                System.out.println("Task id : ");
                task.setId(scanner.nextInt());
                scanner.nextLine();

                System.out.println("Name of task: ");
                task.setName(scanner.nextLine());

                System.out.println("Responsibility of: ");
                task.setAssigned(scanner.nextLine());

                System.out.println("Priority 1-5: ");
                task.setPriority(scanner.nextInt());
                scanner.nextLine();

                System.out.println("Description: ");
                task.setDescription(scanner.nextLine());

                System.out.println("Completed (TRUE/FALSE) : ");
                task.setCompleted(scanner.nextBoolean());
                scanner.nextLine();

                taskStorage1.addTask(task);
                break;

            }
            case "get task": {
                System.out.println("Task id : ");
                int id = scanner.nextInt();
                taskStorage1.getTask(id);
                break;
            }

            case "get all tasks": {
                taskStorage1.getAllTasks();
                System.out.println(taskStorage1.getAllTasks());
                break;
            }

            case "delete task": {
                System.out.println("Task id : ");
                int id = scanner.nextInt();
                taskStorage1.deleteTask(id);
                break;
            }

        }
    }
}



















