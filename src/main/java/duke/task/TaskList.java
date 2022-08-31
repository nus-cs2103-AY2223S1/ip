package duke.task;

import duke.common.DukeException;
import java.util.ArrayList;

/**
 * Handles the list of tasks when duke is running.
 *
 * @author Tan Jun Wei
 */
public class TaskList {
    /** The list of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList object with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with a specified list of tasks.
     *
     * @param tasks The tasks in constructed task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task The task to be added.
     */
    public void addItem(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the task at the given index from the task list.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws DukeException If specified task does not exist.
     */
    public Task deleteItem(int index) throws DukeException{
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        return taskToDelete;
    }

    /**
     * Sets the task at the given index from the task list as completed.
     *
     * @param index The index of the task to be marked.
     * @return The marked task.
     * @throws DukeException If specified task does not exist.
     */
    public Task markItem(int index) throws DukeException{
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToMark = tasks.get(index - 1);
        taskToMark.isDone = true;
        return taskToMark;
    }

    /**
     * Sets the task at the given index from the task list as not completed.
     *
     * @param index The index of the task to be unmarked.
     * @return The task to be unmarked.
     * @throws DukeException If specified task does not exist.
     */
    public Task unmarkItem(int index) throws DukeException {
        if (index > tasks.size() || index < 0) {
            throw new DukeException("Please select a valid item");
        }
        Task taskToUnmark = tasks.get(index - 1);
        taskToUnmark.isDone = false;
        return taskToUnmark;
    }

    /**
     * Returns an encoded string representing the task list.
     *
     * @return An encoded string representing the task list.
     */
    public String encode() {
        StringBuilder encodedTaskList = new StringBuilder();
        for (Task task : tasks) {
            encodedTaskList.append(task.encode()).append("\n");
        }
        return encodedTaskList.toString();
    }

    /**
     * Returns the string representation of the task list.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i ++) {
            taskString.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return taskString.toString();
    }
}
