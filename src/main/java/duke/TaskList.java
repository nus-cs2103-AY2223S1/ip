package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private int capacity;
    private List<Task> tasks;

    /**
     * Default constructor for duke.TaskList creates a duke.TaskList of capacity 100.
     */
    public TaskList() {
        this.capacity = 100;
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Constructor creates a duke.TaskList of the specified capacity.
     *
     * @param capacity The capacity of the duke.TaskList.
     */
    public TaskList(int capacity) {
        this.capacity = capacity;
        this.tasks = new ArrayList<>(capacity);
    }

    /**
     * Method to add a duke.task.Task to duke.TaskList.
     *
     * @param task duke.task.Task to be added to the duke.TaskList.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Overloaded method to add duke.task.Task to specified index in duke.TaskList.
     *
     * @param index Index in duke.TaskList to add duke.task.Task to.
     * @param task duke.task.Task to be added to the duke.TaskList.
     */
    public void add(int index, Task task) {
        this.tasks.add(index, task);
    }

    /**
     * Method to access duke.task.Task at specified index in duke.TaskList.
     *
     * @param index Index of duke.task.Task to be accessed.
     * @return A duke.task.Task object.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Method to delete duke.task.Task from duke.TaskList.
     *
     * @param index Index of duke.task.Task to be deleted.
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Method changes the isDone status of task at the specified index in task list.
     *
     * @param index Index of task in task list.
     * @param isDone isDone status to be updated to the task at the specified index.
     */
    public void changeDoneStatus(int index, boolean isDone) {
        if (isDone) {
            this.tasks.get(index).markAsDone();
        } else {
            this.tasks.get(index).markAsNotDone();
        }
    }

    /**
     * Method gets the current size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }
}
