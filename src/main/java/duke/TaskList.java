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
     * Add task into the TaskList.
     *
     * @param task the task to be added into the TaskList.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove task from the TaskList.
     *
     * @param index the index of the task to be removed from the TaskList.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Get task from the TaskList by the index.
     *
     * @param index the index of Task to be retrieved from the TaskList.
     * @return the task retrieved from the TaskList.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Return the size of the TaskList.
     *
     * @return the number of elements in the TaskList.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Assign the Task at the position of the index.
     *
     * @param index the position of the task to be assigned to.
     * @param task the Task to be assigned into the TaskList.
     */
    public void setTask(int index, Task task) {
        this.taskList.set(index, task);
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
