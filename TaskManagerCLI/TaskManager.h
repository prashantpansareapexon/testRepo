#ifndef TASKMANAGER_H
#define TASKMANAGER_H

#include "Task.h"
#include <vector>
#include <optional>

class TaskManager {
private:
    std::vector<Task> tasks;
    int nextId;

public:
    TaskManager();

    void addTask(const std::string& title, const std::string& description, const std::string& dueDate, TaskPriority priority);
    bool updateTask(int id, const std::string& newTitle, const std::string& newDescription, const std::string& newDueDate, TaskPriority newPriority);
    bool deleteTask(int id);
    bool markTaskComplete(int id);
    std::optional<Task> getTaskById(int id) const;
    std::vector<Task> getAllTasks() const;

    void loadTasks(const std::vector<Task>& loadedTasks);
    std::vector<Task> exportTasks() const;
};

#endif // TASKMANAGER_H
