package sally.task;

import java.util.ArrayList;

/**
 * TaskList class to represent the list of tasks stored.
 *
 * @author liviamil
 */
public class TaskList {
    protected ArrayList<Task> tasks;
    protected int numOfTasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets total number of tasks in task list.
     *
     * @return total number of tasks in task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Gets specific task according to their index.
     *
     * @param index the task index to be returned
     * @return the task of the specific index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Deletes specific task according to their index.
     *
     * @param index the task index to be deleted
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Gets all tasks from task list in ArrayList<Task>
     *
     * @return task list in the form of ArrayList
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Adds inputted task to the existing task list
     *
     * @param task to be added in
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

}
