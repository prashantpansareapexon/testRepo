#include "Menu.h"
#include "Utils.h"
#include <iostream>

Menu::Menu(TaskManager& manager) : taskManager(manager) {}

void Menu::showMainMenu() const {
    std::cout << "\n=== Task Manager ===\n";
    std::cout << "1. Add Task\n";
    std::cout << "2. View All Tasks\n";
    std::cout << "3. Update Task\n";
    std::cout << "4. Delete Task\n";
    std::cout << "5. Mark Task as Complete\n";
    std::cout << "6. Exit\n";
    std::cout << "Choose an option: ";
}

void Menu::handleAddTask() {
    std::string title, description, dueDate;
    int priorityInput;

    std::cout << "Enter title: ";
    std::getline(std::cin, title);

    std::cout << "Enter description: ";
    std::getline(std::cin, description);

    std::cout << "Enter due date (YYYY-MM-DD): ";
    std::getline(std::cin, dueDate);

    if (!Utils::isValidDate(dueDate)) {
        std::cout << "Invalid date format!\n";
        return;
    }

    std::cout << "Enter priority (0: Low, 1: Medium, 2: High): ";
    std::cin >> priorityInput;
    std::cin.ignore(); // to flush newline

    if (priorityInput < 0 || priorityInput > 2) {
        std::cout << "Invalid priority!\n";
        return;
    }

    taskManager.addTask(title, description, dueDate, static_cast<TaskPriority>(priorityInput));
    std::cout << "Task added successfully.\n";
}

void Menu::handleViewTasks() const {
    auto tasks = taskManager.getAllTasks();
    if (tasks.empty()) {
        std::cout << "No tasks found.\n";
        return;
    }

    for (const auto& task : tasks) {
        std::cout << "---------------------\n";
        std::cout << task.toString() << "\n";
    }
}

void Menu::handleUpdateTask() {
    int id;
    std::cout << "Enter Task ID to update: ";
    std::cin >> id;
    std::cin.ignore();

    auto taskOpt = taskManager.getTaskById(id);
    if (!taskOpt.has_value()) {
        std::cout << "Task not found!\n";
        return;
    }

    std::string newTitle, newDescription, newDueDate;
    int newPriorityInput;

    std::cout << "Enter new title: ";
    std::getline(std::cin, newTitle);

    std::cout << "Enter new description: ";
    std::getline(std::cin, newDescription);

    std::cout << "Enter new due date (YYYY-MM-DD): ";
    std::getline(std::cin, newDueDate);

    if (!Utils::isValidDate(newDueDate)) {
        std::cout << "Invalid date format!\n";
        return;
    }

    std::cout << "Enter new priority (0: Low, 1: Medium, 2: High): ";
    std::cin >> newPriorityInput;
    std::cin.ignore();

    if (newPriorityInput < 0 || newPriorityInput > 2) {
        std::cout << "Invalid priority!\n";
        return;
    }

    if (taskManager.updateTask(id, newTitle, newDescription, newDueDate, static_cast<TaskPriority>(newPriorityInput))) {
        std::cout << "Task updated successfully.\n";
    } else {
        std::cout << "Failed to update task.\n";
    }
}

void Menu::handleDeleteTask() {
    int id;
    std::cout << "Enter Task ID to delete: ";
    std::cin >> id;
    std::cin.ignore();

    if (taskManager.deleteTask(id)) {
        std::cout << "Task deleted successfully.\n";
    } else {
        std::cout << "Task not found!\n";
    }
}

void Menu::handleMarkTaskComplete() {
    int id;
    std::cout << "Enter Task ID to mark as complete: ";
    std::cin >> id;
    std::cin.ignore();

    if (taskManager.markTaskComplete(id)) {
        std::cout << "Task marked as complete.\n";
    } else {
        std::cout << "Task not found!\n";
    }
}

void Menu::run() {
    int choice;
    while (true) {
        showMainMenu();
        std::cin >> choice;
        std::cin.ignore(); // flush newline character

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
                std::cout << "Exiting...\n";
                return;
            default:
                std::cout << "Invalid choice!\n";
        }
    }
}
