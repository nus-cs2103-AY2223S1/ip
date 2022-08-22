package duke.duke.task;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class that contains all the Task in program.
 */
public class TaskList {
    private final ArrayList<duke.task.Task> tasks;

    /**
     * Constructor for TaskList.
     * Initialises an ArrayList of Task.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * Initialises an ArrayList of Task.
     * @param tasks The list of tasks from saved file.
     */
    public TaskList(ArrayList<duke.task.Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Task.
     * @param task The Task to be added.
     */
    public void addTask(duke.task.Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a Task.
     * @param num The index of Task to be deleted.
     */
    public void deleteTask(int num) {
        this.tasks.remove(num);
    }

    /**
     * Marks a Task as not done.
     * @param num The index of Task to be marked as not done.
     */
    public void unMarkTask(int num) {
        this.tasks.get(num).markDone();
    }

    /**
     * Marks a Task as done.
     * @param num The index of task to be marked as done.
     */
    public void markTask(int num) {
        this.tasks.get(num).unMarkDone();
    }

    /**
     * Gets the number of Tasks currently in TaskList.
     * @return The size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets a specific Task from TaskList.
     * @param num The index of Task in TaskList.
     * @return The specific Task.
     */
    public duke.task.Task getTask(int num) {
        return this.tasks.get(num);
    }

    /**
     * Getter for tasks.
     * @return The list of all Tasks in program.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
