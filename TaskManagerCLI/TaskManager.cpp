#include "TaskManager.h"

TaskManager::TaskManager() : nextId(1) {}

void TaskManager::addTask(const std::string& title, const std::string& description, const std::string& dueDate, TaskPriority priority) {
    tasks.emplace_back(nextId++, title, description, dueDate, priority);
}

bool TaskManager::updateTask(int id, const std::string& newTitle, const std::string& newDescription, const std::string& newDueDate, TaskPriority newPriority) {
    for (auto& task : tasks) {
        if (task.getId() == id) {
            task.setTitle(newTitle);
            task.setDescription(newDescription);
            task.setDueDate(newDueDate);
            task.setPriority(newPriority);
            return true;
        }
    }
    return false;
}

bool TaskManager::deleteTask(int id) {
    for (auto it = tasks.begin(); it != tasks.end(); ++it) {
        if (it->getId() == id) {
            tasks.erase(it);
            return true;
        }
    }
    return false;
}

bool TaskManager::markTaskComplete(int id) {
    for (auto& task : tasks) {
        if (task.getId() == id) {
            task.markComplete();
            return true;
        }
    }
    return false;
}

std::optional<Task> TaskManager::getTaskById(int id) const {
    for (const auto& task : tasks) {
        if (task.getId() == id) {
            return task;
        }
    }
    return std::nullopt;
}

std::vector<Task> TaskManager::getAllTasks() const {
    return tasks;
}

void TaskManager::loadTasks(const std::vector<Task>& loadedTasks) {
    tasks = loadedTasks;
    nextId = 1;
    for (const auto& task : tasks) {
        if (task.getId() >= nextId) {
            nextId = task.getId() + 1;
        }
    }
}

std::vector<Task> TaskManager::exportTasks() const {
    return tasks;
}
