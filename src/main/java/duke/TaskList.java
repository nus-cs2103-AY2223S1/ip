package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    private final String HORIZONTAL_LINE_BREAK = "-------------------------";

    /**
     * Default class constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Class constructor.
     *
     * @param list ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Returns the list of tasks.
     *
     * @return ArrayList of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Returns a specified task corresponding to the index.
     *
     * @param index Index of tasks in the list.
     * @return Task object.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return Integer representing the size of the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds task into the list.
     *
     * @param task Task object to be added into the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a specified task using index.
     *
     * @param index Integer representing the index of task to be marked.
     * @throws DukeException
     */
    public void markTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task completedTask = tasks.get(index);
            completedTask.markAsDone();
        }
    }

    /**
     * Unmarks a specified task using index.
     *
     * @param index Integer representing the index of task to be unmarked.
     * @throws DukeException
     */
    public void unMarkTask(int index) throws DukeException{
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task unfinishedTask = tasks.get(index);
            unfinishedTask.markAsNotDone();
        }
    }

    /**
     * Deletes a specified task using index.
     *
     * @param index Integer representing the index of task to be deleted.
     * @return Task to be deleted.
     * @throws DukeException
     */
    public Task deleteTask(int index) throws DukeException {
        int size = tasks.size();
        if (index >= size || index < 0) {
            throw new DukeException("The index of the task does not exists.");
        } else {
            Task toBeDeleted = tasks.get(index);
            tasks.remove(index);
            return toBeDeleted;
        }
    }
}
