package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulates an ArrayList of Tasks
 */
public class TaskList {

    public final ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList instance which consists of empty ArrayList of Task.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList instance which consists of non-empty ArrayList of Task.
     *
     * @param taskList the TaskList saved by previous run of Duke program.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the status of emptiness of the current TaskList.
     *
     * @return true if and only if the TaskList consisting of zero element.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Returns the taskList parameter in the TaskList class.
     *
     * @return taskList from the parameter.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

}
