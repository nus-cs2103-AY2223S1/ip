package duke.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.tasks.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add a new task.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task.
     *
     * @param index Index of the task as printed by TaskList's toString.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Return the number of tasks in the task list currently.
     *
     * @return Number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the task with the input index.
     *
     * @param index Index of the task as printed by viewAllTask.
     * @return Task with the input index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Mark a task as done.
     *
     * @param index Position of the task as indicated by TaskList's string
     *              representation.
     */
    public void markTask(int index) {
        Task task = this.tasks.get(index);
        task.setDone();
    }

    /**
     * Mark a task as not done.
     *
     * @param index Position of the task as indicated by TaskList's string
     *              representation.
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
        Pattern pattern = Pattern.compile(keyword);
        for (Task task : this.tasks) {
            Matcher matcher = pattern.matcher(task.getDescription());
            if (matcher.find()) {
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
