package duke.io;

import java.util.ArrayList;

import duke.types.Task;

/**
 * Represents a list of tasks.
 *
 * @author Aaron Tan
 */
public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task into the task list.
     *
     * @param task Task to be added.
     */
    protected void add(Task task) {
        tasks.add(task);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return A boolean checking whether the task is empty.
     */
    protected boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a task at index index.
     *
     * @param index Index of task to be returned.
     * @return Task at index index.
     */
    protected Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task at index index.
     *
     * @param index Index of task to be removed.
     * @return Task at index index.
     */
    protected Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the size of the current task list.
     *
     * @return Size of current task list.
     */
    protected int size() {
        return tasks.size();
    }
}
