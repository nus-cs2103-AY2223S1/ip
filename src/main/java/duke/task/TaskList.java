package duke.task;

import java.util.ArrayList;

/**
 * Represents a task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Initialises the task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Method to add a task to the task list.
     *
     * @param task
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Method to get a task from the task list.
     *
     * @param index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Method to remove a task from the task list.
     *
     * @param index
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Method to check if the task list is empty.
     *
     * @return isEmpty
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Method to return the size of the task list.
     *
     * @return size
     */
    public int size() {
        return taskList.size();
    }
}
