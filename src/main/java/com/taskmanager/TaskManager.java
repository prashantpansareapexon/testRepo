package com.taskmanager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * TaskManager handles CRUD operations and persistence for Task objects.
 */
public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;
    private static final Path TASKS_FILE = Paths.get("tasks.json");

    public TaskManager() {
        loadFromFile();
    }

    /**
     * Add a new task and persist changes.
     */
    public void addTask(String title, String description, String dueDate, TaskPriority priority) {
        LocalDate date = LocalDate.parse(dueDate);
        Task task = new Task(nextId++, title, description, date, priority);
        tasks.add(task);
        saveToFile();
    }

    /**
     * Update an existing task by id.
     */
    public boolean updateTask(int id, String newTitle, String newDescription, String newDueDate, TaskPriority newPriority) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setDueDate(LocalDate.parse(newDueDate));
                task.setPriority(newPriority);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Delete a task by id.
     */
    public boolean deleteTask(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Mark a task as complete by id.
     */
    public boolean markTaskComplete(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markComplete();
                saveToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve a task by id.
     */
    public Optional<Task> getTaskById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    /**
     * Retrieve all tasks.
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Load tasks from an external list and persist.
     */
    public void loadTasks(List<Task> loadedTasks) {
        tasks.clear();
        tasks.addAll(loadedTasks);
        nextId = 1;
        for (Task t : tasks) {
            if (t.getId() >= nextId) {
                nextId = t.getId() + 1;
            }
        }
        saveToFile();
    }

    /**
     * Export tasks for external use.
     */
    public List<Task> exportTasks() {
        return getAllTasks();
    }

    /**
     * Load tasks from JSON file.
     */
    private void loadFromFile() {
        if (Files.exists(TASKS_FILE)) {
            try {
                String content = Files.readString(TASKS_FILE, StandardCharsets.UTF_8);
                JSONArray arr = new JSONArray(content);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    int id = obj.getInt("id");
                    String title = obj.getString("title");
                    String description = obj.getString("description");
                    LocalDate dueDate = LocalDate.parse(obj.getString("dueDate"));
                    TaskPriority priority = TaskPriority.valueOf(obj.getString("priority"));
                    boolean completed = obj.optBoolean("completed", false);
                    Task task = new Task(id, title, description, dueDate, priority);
                    if (completed) {
                        task.markComplete();
                    }
                    tasks.add(task);
                    if (id >= nextId) {
                        nextId = id + 1;
                    }
                }
            } catch (IOException | JSONException e) {
                System.err.println("Failed to load tasks: " + e.getMessage());
            }
        }
    }

    /**
     * Save tasks to JSON file.
     */
    private void saveToFile() {
        JSONArray arr = new JSONArray();
        for (Task t : tasks) {
            JSONObject obj = new JSONObject();
            obj.put("id", t.getId());
            obj.put("title", t.getTitle());
            obj.put("description", t.getDescription());
            obj.put("dueDate", t.getDueDate().toString());
            obj.put("priority", t.getPriority().name());
            obj.put("completed", t.isCompleted());
            arr.put(obj);
        }
        try {
            Files.writeString(TASKS_FILE, arr.toString(2), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }
}