package duke.storage;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Acts as the storage of the Tasks of the users.
 * This class contain an ArrayList as attribute which stores the Tasks.
 */

public class TaskRecords {
    private ArrayList<Task> lst;

    /**
     * Constructor of TaskRecords.
     * Creates and assigns an empty ArrayList to lst attribute.
     */
    public TaskRecords() {
        this.lst = new ArrayList<Task>();
    }

    /**
     * Adds the Task into the attribute of TaskRecords.
     *
     * @param task the task to be added as per request by user's input
     */
    public void addProcess(Task task) {
        if (!this.lst.contains(task)) {
            this.lst.add(task);
        } else {
            this.lst.set(lst.indexOf(task), task);
        }
    }

    /**
     * Adds the Task into the attribute of TaskRecords.
     *
     * @param idx the index position of to-be-deleted task in ArrayList.
     * @return the deleted tasks.
     */
    public Task delete(int idx) {
        Task currTask = this.lst.get(idx);
        this.lst.remove(idx);
        return currTask;
    }

    public Task get(int idx) {
        return this.lst.get(idx);
    }

    public ArrayList<Task> getList() {
        return this.lst;
    }
}
