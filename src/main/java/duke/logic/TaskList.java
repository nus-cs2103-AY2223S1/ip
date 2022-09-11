package duke.logic;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskList stores user tasks and manages them.
 *
 * @author totsukatomofumi
 */
public class TaskList extends ArrayList<Task> {

    /** Storage object for writing task history to a file. */
    private Storage storage;

    private TaskList() {
        super();
    }

    /**
     * Constructs a task list.
     *
     * @param storage the storage object to be tied to this task list.
     */
    public TaskList(Storage storage) {
        super();
        this.storage = storage;
        //retrieve or else clear the file
        try {
            this.storage.retrieveFromStorage(this);
        //thrown
        } catch (IOException e) {
            //make sure file is not deleted, else make again
            this.storage.createRequiredFiles();
            this.storage.clear();
        }
    }

    /**
     * Checks if a task exists at that zero-based index.
     * @param query the zero-based index.
     * @return true if a task exists, else false.
     */
    public boolean exists(int query) {
        return query < super.size() && query >= 0;
    }

    private void updateStorage() {
        if (this.storage != null) {
            this.storage.update(this);
        }
    }

    /**
     * Removes the task at the specified position from this task list.
     * @param index the index of the task to be removed.
     * @return the task that was removed from the task list.
     */
    @Override
    public Task remove(int index) {
        Task temp = super.remove(index);
        this.updateStorage();
        return temp;
    }

    /**
     * Appends the specified task to the end of the task list.
     *
     * @param task task to be appended to this task list.
     * @return true.
     */
    @Override
    public boolean add(Task task) {
        boolean bool = super.add(task);
        this.updateStorage();
        return bool;
    }

    /**
     * Marks the specified task in the task list.
     *
     * @param index the index of the task to be marked.
     * @return the task that was marked.
     */
    public Task markTask(int index) {
        this.get(index).mark();
        this.updateStorage();
        return this.get(index);
    }

    /**
     * Unmarks the specified task in the task list.
     *
     * @param index the index of the task to be unmarked.
     * @return the task that was unmarked.
     */
    public Task unmarkTask(int index) {
        this.get(index).unmark();
        this.updateStorage();
        return this.get(index);
    }

    /**
     * Returns a list of tasks that contain the keyword specified.
     *
     * @param keyword the keyword specified to search tasks.
     * @return the list of tasks found to contain the keyword.
     */
    public TaskList search(String keyword) {
        TaskList result = new TaskList();
        for (Task task : this) {
            if (task.contains(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        String list = "";
        int order = 1;
        for (Task task : this) {
            list += order++ + "." + task.toString() + "\n";
        }
        return list;
    }
}
