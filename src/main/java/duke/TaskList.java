package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Keeps track of the current tasks of the user.
 * @author Lim Ai Lin
 */
public class TaskList {

    private final ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints out all tasks in the list.
     */
    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Adds a new task to the list.
     * @param task The specified task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at a specified index.
     * @param i The index of the task to be returned.
     * @return The task at index i.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes the task at the specified index.
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }

    /**
     * Gets the size of the list.
     * @return The number of items in the list.
     */
    public int size() {
        return tasks.size();
    }
}
