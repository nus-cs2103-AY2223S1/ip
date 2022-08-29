package duke.tools;

import java.util.List;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * This class manages the list of tasks that has been registered by the user.
 * This class provides methods to access and modify the tasks stored by the user.
 */
public class TaskList {
    /** The list of tasks stored in Duke. */
    private List<Task> storedTasks;

    public TaskList(List<Task> storedTask) {
        this.storedTasks = storedTask;
    }

    /**
     * Returns the list of tasks stored in Duke.
     *
     * @return A list of tasks stored in Duke.
     */
    public List<Task> getStoredTasks() {
        return this.storedTasks;
    }

    /**
     * Returns the number of tasks stored in the TaskList.
     *
     * @return The number of tasks stored in the TaskList.
     */
    public int getSize() {
        return this.storedTasks.size();
    }

    /**
     * Returns the task in the TaskList at the given index.
     *
     * @param index The position of the task in the TaskList.
     * @return The task at the given index in the TaskList.
     * @throws DukeException
     */
    public Task getTask(int index) throws DukeException {
        try {
            return storedTasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }

    /**
     * Sets the done status of the task at the given index in the TaskList to the provided state.
     *
     * @param index The position of the task in the TaskList.
     * @param isDone The resulting done status of the task.
     * @throws DukeException
     */
    public void setTaskDoneStatus(int index, boolean isDone) throws DukeException {
        try {
            storedTasks.get(index).setIsDone(isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add to the TaskList.
     */
    public void addTask(Task task) {
        storedTasks.add(task);
    }

    /**
     * Deletes a task from the TaskList.
     *
     * @param index The position of the task in the TaskList.
     * @throws DukeException
     */
    public void deleteTask(int index) throws DukeException {
        try {
            storedTasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Exception: Invalid task number.");
        }
    }
}
