package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * A Tasklist object that keeps track of all the tasks on hand.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a Tasklist object.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all the current tasks.
     * 
     * @return An ArrayList of type Task that consists of all the current tasks.
     */
    protected ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Returns the number of tasks currently in the task list.
     *
     * @return the number of tasks
     */
    protected int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves the task in the task list that corresponds to the given index.
     *
     * @param taskIndex the specified index of the task in the task list
     * @return the specified Task corresponding to the given number 
     */
    protected Task getTask(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Adds the given task into the task list.
     *
     * @param task the specified task to be added into the task list.
     */
    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task in the task list that corresponds to the given index.
     *
     * @param taskIndex the specified index of the task in the task list
     * @return the task that is to be deleted
     */
    protected Task deleteTask(int taskIndex) { 
        return tasks.remove(taskIndex);
    }
}
