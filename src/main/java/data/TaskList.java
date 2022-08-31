package data;

import data.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private final List<Task> tasks;

    /**
     * Constructs a blank task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a particular task as done.
     * @param idx Index of the task to be marked as done.
     * @return Task that was marked done.
     */
    public Task markDone(int idx) {
        Task task = tasks.get(idx);
        task.markDone();
        return task;
    }

    /**
     * Deletes a particular task.
     * @param idx Index of the task to be deleted.
     * @return Task that was deleted.
     */
    public Task delete(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        return task;
    }

    /**
     * Adds a new task to the list.
     * @param task Task to be added.
     * @return Task that was added.
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Returns an array of the string representation of each task in the list.
     * @return Array of string representations.
     */
    public String[] toStringArr() {
        String[] taskStrings = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            taskStrings[i] = (i + 1) + ". " + tasks.get(i).toString();
        }
        return taskStrings;
    }
}
