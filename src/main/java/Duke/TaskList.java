package duke;

import java.util.ArrayList;

/**
 * List of tasks for Duke application.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<Task>(100);
    }

    /**
     * Returns the size of the taskList.
     *
     * @return size of taskList.
     */
    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Adds task to taskList without any output.
     *
     * @param task task to be added to taskList.
     */
    public void addTaskWithoutOutput(Task task) {
        list.add(task);
    }

    /**
     * Adds task to taskList with output from UI.
     *
     * @param task task to be added to taskList.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes task from taskList.
     *
     * @param index index of task to be deleted.
     * @throws IllegalIndexException if index is invalid.
     */
    public void deleteTask(int index) throws IllegalIndexException {
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.remove(index);
        }
    }

    /**
     * Marks task in taskList.
     *
     * @param index index of task to be marked.
     * @throws IllegalIndexException if index is invalid.
     */
    public void mark(int index) throws IllegalIndexException {
        //  error checking
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setDone();
        }
    }

    /**
     * Unmarks task in taskList.
     *
     * @param index index of task to be unmarked.
     * @throws IllegalIndexException if index is invalid.
     */
    public void unmark(int index) throws IllegalIndexException {
        //  error checking
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setNotDone();
        }
    }
}
