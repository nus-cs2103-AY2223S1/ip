package duke.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Duke application.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor to create an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index Index of task to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index Index of the task to be retrieved.
     * @return The task to be retrieved.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
