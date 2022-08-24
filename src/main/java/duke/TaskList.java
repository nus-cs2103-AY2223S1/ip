package duke;

import java.util.ArrayList;
import java.util.List;

import duke.Task;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     * @param index the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     * @param index the index of the task to remove
     * @return the task that was removed
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as complete.
     * @param index the index of the task to mark as complete
     */
    public void markAsDone(int index) {
        this.tasks.get(index).setComplete(true);
    }

    /**
     * Marks the task at the specified index as incomplete.
     * @param index the index of the task to mark as incomplete
     */
    public void markAsUndone(int index) {
        this.tasks.get(index).setComplete(false);
    }

    public TaskList filter(String searchTerm) {
        // TODO
        return new TaskList();
    }

    @Override
    public String toString() {
        // TODO
        return tasks.toString();
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }
}
