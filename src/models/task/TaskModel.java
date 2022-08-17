package models.task;

import java.util.ArrayList;
import java.util.List;

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

    // taskId from 1-length
    public Task markTask(Integer taskId) throws IllegalArgumentException {
        try {
            Task task = tasks.get(taskId - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(String.format("Task number %d does not exist :(", taskId));
        }
    }

    /**
     * @return a List of all the Tasks at the current time
     */
    public List<Task> getAllTasks() {
        return tasks;
    }
}
