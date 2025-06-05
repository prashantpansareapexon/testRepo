package com.taskmanager;

import com.taskmanager.TaskManager;
import com.taskmanager.Menu;
import com.taskmanager.Task.TaskPriority;

/**
 * Entry point for the Task Manager CLI application.
 */
public class App {
    public static void main(String[] args) {
        // Initialize TaskManager (loads existing tasks)
        TaskManager taskManager = new TaskManager();

        // Sample tasks (optional initialization)
        taskManager.addTask(
            "Complete C++ project",
            "Finish all parts of the CLI Task Manager",
            "2025-05-01",
            TaskPriority.HIGH
        );
        taskManager.addTask(
            "Write documentation",
            "Document the project and its components",
            "2025-04-30",
            TaskPriority.MEDIUM
        );

        // Initialize and run menu
        Menu menu = new Menu(taskManager);
        try {
            menu.run();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}