#ifndef MENU_H
#define MENU_H

#include "TaskManager.h"

class Menu {
private:
    TaskManager& taskManager;

    void showMainMenu() const;
    void handleAddTask();
    void handleViewTasks() const;
    void handleUpdateTask();
    void handleDeleteTask();
    void handleMarkTaskComplete();

public:
    Menu(TaskManager& manager);

    void run();
};

#endif // MENU_H
