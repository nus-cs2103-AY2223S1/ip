package duke;

import java.util.ArrayList;

/**
 * TaskList object class that stores ArrayList of Tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object with empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object with a list of tasks.
     *
     * @param listOfTasks list of tasks to store.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.tasks = listOfTasks;
    }

    /**
     * Adds a Task object to list of tasks in TaskList object.
     *
     * @param task object added to list of tasks.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a Task object from list of tasks in TaskList object.
     *
     * @param index index of object removed from list of tasks.
     */
    public void remove(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Gets the size of the list of tasks in TaskList object.
     *
     * @return size of the list of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets task at specified index of list of tasks in TaskList object.
     *
     * @param index position of task in list of tasks in TaskList object.
     * @return Task at specified position of list of tasks in TaskList object.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets list of tasks in TaskList object.
     *
     * @return ArrayList of Task representing list of tasks stored.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.tasks;
    }

    /**
     * Marks task at specified index in list of tasks as done.
     *
     * @param index position of task in list of tasks to be marked done.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Marks task at specified index in list of tasks as not done.
     *
     * @param index position of task in list of tasks to be marked not done.
     */
    public void markAsNotDone(int index) {
        this.tasks.get(index).markAsNotDone();
    }
}
