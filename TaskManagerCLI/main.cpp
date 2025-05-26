#include <iostream>
#include "Task.h"
#include "TaskManager.h"
#include "Menu.h"

int main() {
    TaskManager taskManager;

    // Initialize with some sample tasks (optional)
    taskManager.addTask("Complete C++ project", "Finish all parts of the CLI Task Manager", "2025-05-01", TaskPriority::High);
    taskManager.addTask("Write documentation", "Document the project and its components", "2025-04-30", TaskPriority::Medium);

    Menu menu(taskManager);
    menu.run();

    return 0;
}
