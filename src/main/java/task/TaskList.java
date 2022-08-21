package task;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the number of Tasks in the list.
     *
     * @return the number of Tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Return the String representation of the Task at the index.
     *
     * @param index the index of the Task to be represented as a String.
     * @return String representation of the Task at the index.
     */
    public String taskStringAtIndex(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Adds the input Task to the list of tasks.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * The Task at the input index to be removed.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Return the Task from the Task list at the input index.
     *
     * @param index Index of the Task to be returned.
     * @return the Task at the input index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }
}
