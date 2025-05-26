#ifndef TASK_H
#define TASK_H

#include <string>

enum class TaskPriority {
    Low,
    Medium,
    High
};

class Task {
private:
    int id;
    std::string title;
    std::string description;
    std::string dueDate; // format: YYYY-MM-DD
    TaskPriority priority;
    bool completed;

public:
    Task(int id, const std::string& title, const std::string& description, const std::string& dueDate, TaskPriority priority);

    // Getters
    int getId() const;
    std::string getTitle() const;
    std::string getDescription() const;
    std::string getDueDate() const;
    TaskPriority getPriority() const;
    bool isCompleted() const;

    // Setters
    void setTitle(const std::string& newTitle);
    void setDescription(const std::string& newDescription);
    void setDueDate(const std::string& newDueDate);
    void setPriority(TaskPriority newPriority);
    void markComplete();

    std::string toString() const;
};

#endif // TASK_H
