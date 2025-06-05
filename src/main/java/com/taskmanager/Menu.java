package com.taskmanager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Menu class for interacting with the TaskManager via console.
 */
public class Menu {
    private final TaskManager taskManager;
    private final Scanner scanner;

    public Menu(TaskManager manager) {
        this.taskManager = manager;
        this.scanner = new Scanner(System.in);
    }

    private void showMainMenu() {
        System.out.println("\n=== Task Manager ===");
        System.out.println("1. Add Task");
        System.out.println("2. View All Tasks");
        System.out.println("3. Update Task");
        System.out.println("4. Delete Task");
        System.out.println("5. Mark Task as Complete");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private void handleAddTask() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Enter due date (YYYY-MM-DD): ");
        String dueDate = scanner.nextLine().trim();

        if (!Utils.isValidDate(dueDate)) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.print("Enter priority (0: Low, 1: Medium, 2: High): ");
        int priorityInput;
        try {
            priorityInput = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid priority input!");
            return;
        }

        if (priorityInput < 0 || priorityInput > 2) {
            System.out.println("Invalid priority!");
            return;
        }

        TaskPriority priority = TaskPriority.values()[priorityInput];
        taskManager.addTask(title, description, dueDate, priority);
        System.out.println("Task added successfully.");
    }

    private void handleViewTasks() {
        List<Task> tasks = taskManager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }

        for (Task task : tasks) {
            System.out.println("---------------------");
            System.out.println(task.toString());
        }
    }

    private void handleUpdateTask() {
        System.out.print("Enter Task ID to update: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input!");
            return;
        }

        Optional<Task> taskOpt = taskManager.getTaskById(id);
        if (!taskOpt.isPresent()) {
            System.out.println("Task not found!");
            return;
        }

        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine().trim();

        System.out.print("Enter new due date (YYYY-MM-DD): ");
        String newDueDate = scanner.nextLine().trim();

        if (!Utils.isValidDate(newDueDate)) {
            System.out.println("Invalid date format!");
            return;
        }

        System.out.print("Enter new priority (0: Low, 1: Medium, 2: High): ");
        int newPriorityInput;
        try {
            newPriorityInput = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid priority input!");
            return;
        }

        if (newPriorityInput < 0 || newPriorityInput > 2) {
            System.out.println("Invalid priority!");
            return;
        }

        TaskPriority newPriority = TaskPriority.values()[newPriorityInput];
        boolean updated = taskManager.updateTask(id, newTitle, newDescription, newDueDate, newPriority);
        if (updated) {
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Failed to update task.");
        }
    }

    private void handleDeleteTask() {
        System.out.print("Enter Task ID to delete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input!");
            return;
        }

        boolean deleted = taskManager.deleteTask(id);
        if (deleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found!");
        }
    }

    private void handleMarkTaskComplete() {
        System.out.print("Enter Task ID to mark as complete: ");
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID input!");
            return;
        }

        boolean marked = taskManager.markTaskComplete(id);
        if (marked) {
            System.out.println("Task marked as complete.");
        } else {
            System.out.println("Task not found!");
        }
    }

    public void run() {
        while (true) {
            showMainMenu();
            String choiceLine = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(choiceLine);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {
                case 1:
                    handleAddTask();
                    break;
                case 2:
                    handleViewTasks();
                    break;
                case 3:
                    handleUpdateTask();
                    break;
                case 4:
                    handleDeleteTask();
                    break;
                case 5:
                    handleMarkTaskComplete();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}