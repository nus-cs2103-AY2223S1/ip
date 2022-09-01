package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Keeps track of the current tasks of the user.
 *
 * @author Lim Ai Lin
 */
public class TaskList {

    private final ArrayList<Task> TASKS;
    public TaskList(ArrayList<Task> tasks) {
        this.TASKS = tasks;
    }

    /**
     * Prints out all tasks in the list.
     */
    public void list() {
        for (int i = 0; i < TASKS.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + TASKS.get(i).toString());
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The specified task to be added.
     */
    public void add(Task task) {
        TASKS.add(task);
    }

    /**
     * Gets the task at a specified index.
     *
     * @param i The index of the task to be returned.
     * @return The task at index i.
     */
    public Task get(int i) {
        return TASKS.get(i);
    }

    /**
     * Removes the task at the specified index.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        TASKS.remove(i);
    }

    /**
     * Gets the size of the list.
     *
     * @return The number of items in the list.
     */
    public int size() {
        return TASKS.size();
    }
}
