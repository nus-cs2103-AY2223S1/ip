package ava.processor;

import java.util.ArrayList;

import ava.task.Task;

/**
 * Class to represent the tasks lists.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * The constructor for TaskList with no parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * The constructor for TaskList with an input of ArrayList.
     *
     * @param task An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> task) {
        this.tasks = task;
    }

    /**
     * Adds a specific tasks to the TaskList.
     *
     * @param t A specific tasks to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a specific tasks from the TaskList.
     *
     * @param i Specific tasks's index number.
     * @return Task taskDeleted.
     */
    public Task delete(int i) {
        return tasks.remove(i);
    }

    /**
     * Marks a specific tasks of the TaskList.
     *
     * @param i Specific tasks's index number.
     */
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Marks a specific tasks of the TaskList.
     *
     * @param i Specific tasks's index number.
     */
    public void markUndone(int i) {
        tasks.get(i).markUndone();
    }

    /**
     * Returns the number of tasks inside the TaskList.
     *
     * @return int size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a specific tasks from the TaskList.
     *
     * @param i Specific tasks's index number.
     * @return Task object.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the current ArrayList.
     *
     * @return Arraylist.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
