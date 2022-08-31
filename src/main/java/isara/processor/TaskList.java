package isara.processor;

import java.util.ArrayList;

import isara.task.Task;

/**
 * Class that represents the list of tasks inputted by the user.
 *
 * @author Melissa Anastasia Harijanto
 */
public class TaskList {
    /**
     * The tasks inputted by the user.
     */
    protected ArrayList<Task> tasks;
    /**
     * The number of tasks inputted by the user.
     */
    protected int numberOfTasks;

    /**
     * Constructs an instance of TaskList.
     * This initiates an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    /**
     * Constructs an instance of TaskList;
     * the task list initiated is not empty as it
     * loads the tasks stated in duke.txt.
     *
     * @param tasks The list of tasks that is loaded into the bot.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numberOfTasks = tasks.size();
    }

    /**
     * Adds a task into the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task that is to be removed.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets a task from the list.
     *
     * @param index The index of the task.
     * @return The task that is requested by the user.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }
}
