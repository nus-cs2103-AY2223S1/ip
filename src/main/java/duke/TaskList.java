package duke;

import task.Task;

import java.util.ArrayList;

/**
 * The class that contains the task list in the Duke program.
 *
 * @author ShummyOwnzYou
 * @version 0.1
 */

public class TaskList {

    public final ArrayList<Task> TASKS;

    /**
     * Initializes a TaskList object with an empty ArrayList.
     */

    public TaskList() {
        TASKS = new ArrayList<>();
    }

    /**
     * Initializes a TaskList object with the given ArrayList.
     *
     * @param taskList The given ArrayList.
     */

    public TaskList(ArrayList<Task> taskList) {
        TASKS = taskList;
    }

    /**
     * Adds the given task.
     *
     * @param task The task to be added.
     */

    public void addTask(Task task) {
        TASKS.add(task);
    }

    /**
     * Removes the given task through accessing its index.
     *
     * @param index The index of the task to be removed.
     * @return The removed Task.
     */

    public Task removeTask(int index) {
        return TASKS.remove(index);
    }

    /**
     * Marks the given task through accessing its index.
     *
     * @param index The index of the task to be marked.
     */

    public void markTask(int index) {
        TASKS.get(index).toggleDoneness();
    }

    /**
     * Unmarks the given task through accessing its index.
     *
     * @param index The index of the task to be unmarked.
     */

    public void unmarkTask(int index) {
        TASKS.get(index).toggleDoneness();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return The size of the TaskList.
     */

    public int size() {
        return TASKS.size();
    }
}
