package models.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages state for Tasks and exposes methods for CRUD. Injected into Commands that require usage.
 */
public class TaskModel {
    List<Task> tasks;
    public TaskModel() {
        this.tasks = new ArrayList<>();
    }

    /** Creates and adds a new Task, then returns the newly added Task.
     * @param description
     * @return The task created
     */
    public Task addTask(String description) {
        Task newTask = new ConcreteTask(description);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * @return a Collection of all the Tasks at the current time
     */
    public Collection<Task> getAllTasks() {
        return tasks;
    }


}
