package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import duke.DukeException;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new {@code TaskList} with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new {@code TaskList} with given tasks.
     *
     * @param tasks The tasks to initialize the {@code TaskList} with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a new {@code TaskList} with given tasks.
     *
     * @param tasks The tasks to initialize the {@code TaskList} with.
     */
    public TaskList(Task... tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(Arrays.asList(tasks));
    }

    /**
     * Returns an iterator over the tasks in this {@code TaskList}.
     *
     * @return An iterator over the tasks in this {@code TaskList}.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }

    /**
     * Returns the string representation of this {@code TaskList}.
     *
     * @return The string representation of this {@code TaskList}.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.format("\t%d. %s", i + 1, tasks.get(i)));
            if (i + 1 < tasks.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks in this {@code TaskList}.
     *
     * @return The number of tasks in this {@code TaskList}.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index The index of the task to return. Assumed 0-indexed.
     * @return The task at the given index.
     */
    public Task get(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bound");
        }
        return tasks.get(index);
    }

    /**
     * Removes the task at the given index.
     *
     * @param index The index of the task to remove. Assumed 0-indexed.
     */
    public void remove(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Index out of bound");
        }
        tasks.remove(index);
    }

    /**
     * Adds a given task to this {@code TaskList}.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }
}
