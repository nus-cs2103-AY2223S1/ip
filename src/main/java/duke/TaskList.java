package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing a list of tasks.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Creates a TaskList with an empty ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with specified input list of tasks.
     *
     * @param tasks A list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a list of tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return Size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Marks task at specified index as complete.
     *
     * @param index 0-indexed position of the task in the list.
     * @return The task at specified index.
     * @throws DukeException If index is negative or greater than size of list.
     */
    public Task markTask(int index) throws DukeException {
        if (index < 0 || index >= size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = tasks.get(index);
        task.mark();
        return task;
    }

    /**
     * Marks task at specified index as incomplete.
     *
     * @param index 0-indexed position of the task in the list.
     * @return The task at specified index.
     * @throws DukeException If index is negative or greater than size of list.
     */
    public Task unmarkTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        Task task = tasks.get(index);
        task.unmark();
        return task;
    }

    /**
     * Removes a task at specified index.
     *
     * @param index 0-indexed position of the task in the list.
     * @return The task at specified index.
     * @throws DukeException If index is negative or greater than size of list.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.size()) {
            throw new DukeException("Task number does not exist.");
        }
        return tasks.remove(index);
    }

    /**
     * Adds specified task into the list.
     *
     * @param task The specified task to be added.
     * @return Task from input parameter.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s\n", 1 + i, tasks.get(i).toString()));
        }
        return result.toString();
    }
}
