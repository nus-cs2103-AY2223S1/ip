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
     * Returns the size of the tasklist.
     *
     * @return size of tasklist.
     */
    public int size() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Adds task to tasklist without any output.
     *
     * @param task task to be added to tasklist.
     */
    public void addTaskWithoutOutput(Task task) {
        list.add(task);
    }

    /**
     * Adds task to tasklist with output from UI.
     *
     * @param task task to be added to tasklist.
     * @param ui user interface of duke.
     */
    public void addTask(Task task, Ui ui) {
        list.add(task);
        ui.addResponse(task, this);
    }

    /**
     * Deletes task from tasklist.
     *
     * @param index index of task to be deleted.
     * @param ui user interface of duke.
     * @throws IllegalIndexException if index is invalid.
     */
    public void deleteTask(int index, Ui ui) throws IllegalIndexException {
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            ui.deleteResponse(this, index);
            list.remove(index);
        }
    }

    /**
     * Marks task in tasklist.
     *
     * @param index index of task to be marked.
     * @param ui user interface of duke.
     * @throws IllegalIndexException if index is invalid.
     */
    public void mark(int index, Ui ui) throws IllegalIndexException {
        //  error checking
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setDone();
            ui.markResponse(this, index);
        }
    }

    /**
     * Unmarks task in tasklist.
     *
     * @param index index of task to be unmarked.
     * @param ui user interface of duke.
     * @throws IllegalIndexException if index is invalid.
     */
    public void unmark(int index, Ui ui) throws IllegalIndexException {
        //  error checking
        if (index < 0 || index >= list.size()) {
            throw new IllegalIndexException("Index invalid!");
        } else {
            list.get(index).setNotDone();
            ui.unmarkResponse(this, index);
        }
    }
}
