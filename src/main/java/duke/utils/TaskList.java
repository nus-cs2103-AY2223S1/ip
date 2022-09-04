package duke.utils;

import java.util.ArrayList;
import java.util.List;

import duke.tasks.Task;

/**
 * Represents a List of Task.
 *
 * @author sikai00
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes a new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the task list currently.
     *
     * @return Number of tasks in the task list
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a new task.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        assert this.tasks.contains(task);
    }

    /**
     * Deletes a task.
     *
     * @param index Index of the task as printed by TaskList's toString
     */
    public void deleteTask(int index) {
        Task deletedTask = this.tasks.remove(index);
        assert !this.tasks.contains(deletedTask);
    }

    /**
     * Returns the task with the input index.
     *
     * @param index Index of the task as printed by viewAllTask
     * @return Task with the input index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index Position of the task as indicated by TaskList's string
     *              representation
     */
    public void markTask(int index) {
        Task task = this.tasks.get(index);
        task.setDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index Position of the task as indicated by TaskList's string
     *              representation
     */
    public void unmarkTask(int index) {
        Task task = this.tasks.get(index);
        task.setNotDone();
    }

    /**
     * Finds and returns a TaskList with subset of tasks with description matching the keyword.
     *
     * @param keyword String to match the descriptions
     * @return TaskList of matching tasks
     */
    public TaskList findMatchingTasks(String keyword) {
        TaskList result = new TaskList();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Return a string representation of all tasks.
     *
     * @return String representation of all tasks
     */
    public String toString() {
        int taskListSize = this.tasks.size();
        // String in java are immutable and leads to O(n^2) time complexity
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < taskListSize; i++) {
            int index = i + 1;
            Task task = this.tasks.get(i);
            String taskStr = index + "." + task + "\n";
            allTasks.append(taskStr);
        }
        return allTasks.toString();
    }
}
