package duke.processor;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Class to represent the TASK lists.
 */
public class TaskList {
    private static ArrayList<Task> TASK;

    /**
     * The constructor for no input
     */
    public TaskList() {
        this.TASK = new ArrayList<Task>();
    }

    /**
     * The constructor with input ArrayList
     * @param task
     */
    public TaskList(ArrayList<Task> task) {
        this.TASK = task;
    }

    /**
     * Method to add a TASK.
     * @param t
     */
    public void add(Task t) {
        TASK.add(t);
    }

    /**
     * Method to remove a TASK.
     * @param i
     * @return Duke.Task.Task taskDeleted
     */
    public Task delete(int i) {
        return TASK.remove(i);
    }

    /**
     * Method to mark a TASK as done.
     * @param i
     */
    public void markDone(int i) {
        TASK.get(i).markDone();
    }

    /**
     * Method to mark a TASK as undone.
     * @param i
     */
    public void markUndone(int i) {
        TASK.get(i).markUndone();
    }

    /**
     * Method to return the number of tasks.
     * @return int
     */
    public int size() {
        return TASK.size();
    }

    /**
     * Method to get a specific TASK.
     * @param i
     * @return Duke.Task.Task object
     */
    public Task get(int i) {
        return TASK.get(i);
    }

    /**
     * Method to get the list of tasks.
     * @return Arraylist
     */
    public ArrayList<Task> getTasks() {
        return this.TASK;
    }
}
