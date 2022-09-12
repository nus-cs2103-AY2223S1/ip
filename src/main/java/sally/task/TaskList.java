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

    /**
     * Constructor for TaskList starting with null
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.numOfTasks = 0;
    }

    /**
     * Constructor for TaskList starting with a given task list
     *
     * @param tasks given task list to be parsed in
     */
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

    /**
     * Finds tasks containing the given keyword.
     *
     * @param keyword to filter out the tasks
     * @return list of tasks containing keyword
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

}
