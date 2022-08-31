package seedu.duke;

import java.util.ArrayList;

/**
 * A wrapper containing an arraylist of tasks.
 */
public class TaskList {
    /* The list of tasks */
    private ArrayList<Task> tasks;

    /**
     * A constructor for Tasklist
     * @param tasks the list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;

    }

    /**
     * A constructor for Tasklist which contains no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a specified task.
     *
     * @param task A task.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task.
     *
     * @param taskNumber The index of the task to be removed in the Tasklist.
     */
    public void remove(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    /**
     * Returns the list of tasks.
     *
     * @return an arraylist of tasks stored.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


}
