#include "Task.h"
#include <sstream>

Task::Task(int id, const std::string& title, const std::string& description, const std::string& dueDate, TaskPriority priority)
    : id(id), title(title), description(description), dueDate(dueDate), priority(priority), completed(false) {}

int Task::getId() const {
    return id;
}

std::string Task::getTitle() const {
    return title;
}

std::string Task::getDescription() const {
    return description;
}

std::string Task::getDueDate() const {
    return dueDate;
}

TaskPriority Task::getPriority() const {
    return priority;
}

bool Task::isCompleted() const {
    return completed;
}

void Task::setTitle(const std::string& newTitle) {
    title = newTitle;
}

void Task::setDescription(const std::string& newDescription) {
    description = newDescription;
}

void Task::setDueDate(const std::string& newDueDate) {
    dueDate = newDueDate;
}

void Task::setPriority(TaskPriority newPriority) {
    priority = newPriority;
}

void Task::markComplete() {
    completed = true;
}

std::string Task::toString() const {
    std::stringstream ss;
    ss << "ID: " << id << "\n"
       << "Title: " << title << "\n"
       << "Description: " << description << "\n"
       << "Due Date: " << dueDate << "\n"
       << "Priority: ";
    switch (priority) {
        case TaskPriority::Low:
        case TaskPriority::Medium:
        case TaskPriority::High:
            ss << "High";
            break;
    }
    ss << "\nStatus: " << (completed ? "Completed" : "Pending") << "\n";
    return ss.str();
}
