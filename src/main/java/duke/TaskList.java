package duke;

import duke.exception.DukeIndexErrorException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList object from the given list of tasks.
     *
     * @param taskList The list of tasks.
     */
    protected TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return The number of tasks in this list.
     */
    protected int size() {
        return tasks.size();
    }

    protected ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the task at the specified index in this list.
     *
     * @param index The index of the task to return.
     * @return The task at the specified index in this list.
     * @throws DukeIndexErrorException If the index is out of range (index < 0 || index >= size()).
     */
    protected Task get(int index) throws DukeIndexErrorException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(tasks.size());
        }
    }

    /**
     * Appends the specified task to the end of this list.
     *
     * @param newTask Task to be appended to this list
     */
    protected void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes the task at the specified position in this list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     *
     * @param index The index of the element to be removed.
     * @return The task at the specified index in this list.
     * @throws DukeIndexErrorException If the index is out of range (index < 0 || index >= size()).
     */
    protected Task remove(int index) throws DukeIndexErrorException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexErrorException(tasks.size());
        }
    }
}
