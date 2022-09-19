package pony;

import java.util.ArrayList;
import pony.task.Task;

/**
 * List of all the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add task to the list if the list does not already contain the task.
     *
     * @param task Task to be added in.
     * @throws PonyException If the list contains that task already.
     */
    public void addTask(Task task) throws PonyException {
        if (tasks.contains(task)) {
            throw new PonyException("My friend, you have already recorded this task!! So forgetful!");
        }
        this.tasks.add(task);
    }

    /**
     * Remove a task from the list.
     *
     * @param index Index of the task to be removed.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Get all the tasks.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Get how many tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int sizeOf() {
        return this.tasks.size();
    }

    /**
     * Get a task from the list using an index.
     *
     * @param index The task index in the list.
     * @return A task.
     * @throws PonyException if the task index is invalid.
     */
    public Task getTask(int index) throws PonyException {
        // Check if index is valid
        if (index < 0 || index >= sizeOf()) {
            throw new PonyException("My friend, I can't find this task number!!");
        }
        assert index < sizeOf() && index >= 0 : "Task index should be valid";
        return tasks.get(index);
    }
}
