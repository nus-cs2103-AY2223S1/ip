package jude;

import java.util.ArrayList;
import java.util.List;

import jude.task.Task;

/**
 * TaskList is a class which stores a list of tasks.
 */
public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Creates a new empty {@code TaskList}.
     */
    public TaskList() {
    }

    /**
     * Adds a {@code Task} to the end of the {@code TaskList}.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    // checks if the index range is out of bounds
    private void checkIndex(int index) {
        if (index <= 0 || index > tasks.size()) {
            throw new IllegalCommandException("Invalid index");
        }
    }

    /**
     * Deletes a task at a particular index (1-based).
     *
     * @param index The index.
     * @throws IllegalCommandException If index range is out of bounds.
     */
    public void delete(int index) {
        checkIndex(index);
        tasks.remove(index - 1);
    }

    /**
     * Returns the task corresponding to the specified index (1-based).
     *
     * @param index The index.
     * @return The {@code Task} corresponding to the specified index.
     */
    public Task get(int index) {
        checkIndex(index);
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of elements in the {@code TaskList} object.
     *
     * @return The number of elements.
     */
    public int size() {
        return tasks.size();
    }
}
