package com.taskmanager;

import java.time.LocalDate;

public class Task {
    public enum TaskPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    private int id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private boolean completed;

    public Task(int id, String title, String description, LocalDate dueDate, TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    // Setters
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    public void setPriority(TaskPriority newPriority) {
        this.priority = newPriority;
    }

    public void markComplete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n")
          .append("Title: ").append(title).append("\n")
          .append("Description: ").append(description).append("\n")
          .append("Due Date: ").append(dueDate).append("\n")
          .append("Priority: ").append(priority).append("\n")
          .append("Status: ").append(completed ? "Completed" : "Pending").append("\n");
        return sb.toString();
    }
}