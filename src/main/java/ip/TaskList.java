package ip;

import ip.exception.NoTaskFound;

import ip.task.Task;

import java.util.LinkedList;

/**
 * Encapsulation of a list of Tasks
 *
 * @author Jonathan Lam
 */
public class TaskList {
    public LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Adds the given task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Index of task to be deleted.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void delete(int index) throws NoTaskFound {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    /**
     * Marks the task at the specified index.
     *
     * @param index Index of task to be unmarked.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void mark(int index) throws NoTaskFound {
        try {
            tasks.get(index).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index Index of task to be unmarked.
     * @throws NoTaskFound If there is no task at that index.
     */
    public void unmark(int index) throws NoTaskFound {
        try {
            tasks.get(index).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFound(index);
        }
    }

    @Override
    public String toString() {
        int taskCount = tasks.size();
        return String.format("You have %d tasks. Get started now!\n", taskCount);
    }
}
