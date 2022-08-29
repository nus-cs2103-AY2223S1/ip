package task;

import exception.DukeException;

import java.util.ArrayList;

/**
 * Represents the TaskList.
 */
public class TaskList {
    protected Task task;
    protected ArrayList<Task> taskList;

    /**
     * Constructor for TaskList if there is a ArrayList.
     *
     * @param tasks ArrayList of the tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
            this.taskList = tasks;
    }

    /**
     * Constructor for TaskList if there is no ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>(100);
    }

    /**
     * Returns the task at the index provided.
     *
     * @param index The index of the task to be returned.
     * @return Returns the task at the index provided.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds the task to the taskList.
     *
     * @param task Task to be added to the taskList.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Prints out all the tasks in the taskList.
     */
    public void forEach() {
        taskList.forEach(n -> System.out.println((taskList.indexOf(n) + 1) + "."
                + n.toString()));
    }

    /**
     * Removes the task at the index provided.
     *
     * @param index The index of the task to be removed.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Changes the taskList to an ArrayList.
     *
     * @return An ArrayList containing all the tasks in the
     * taskList.
     */
    public ArrayList<Task> toArray() {
        return this.taskList;
    }

    /**
     * Returns the size of the taskList.
     *
     * @return Returns the size of the taskList.
     */
    public int size() {
        return this.taskList.size();
    }
}
