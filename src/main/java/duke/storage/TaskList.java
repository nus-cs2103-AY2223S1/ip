package duke.storage;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list that contains different tasks.
 */
public class TaskList {

    protected List<Task> tasks;
    int count = 0;

    /**
     * Constructs a new TaskList instance with no parameter.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance with a given List.
     *
     * @param tasks List of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        count = tasks.size();
    }

    /**
     * Goes through all tasks stored in the list.
     */
    public void iterate() {
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + "." + task.toString());
            i++;
        }
    }

    /**
     * Returns the total number of tasks in the list as String.
     *
     * @return A string.
     */
    public String getCount() {
        return "Now you have " + this.count + " tasks in the list.";
    }

    /**
     * Finds the task in the list given an index.
     *
     * @param index Position of the task in the list.
     * @return The task found.
     */
    public Task findTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        count++;
    }

    /**
     * Delete a task from the list.
     *
     * @param task Task to be removed.
     */
    public void delete(Task task) {
        tasks.remove(task);
        count--;
    }

}
