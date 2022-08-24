package duke.task;

import java.util.ArrayList;

/**
 * The TaskList which stores the users task in an ArrayList.
 *
 * @author Leong Jia Hao Daniel
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Another constructor for the taskList when the input file is
     * not empty.
     *
     * @param list
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Returns the ArrayList that stores the task.
     *
     * @return The ArrayList that stores the task.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks left.
     *
     * @return The number of tasks left.
     */
    public int numberOfTasks() {
        return taskList.size();
    }

    /**
     * Returns a String saying how many tasks the user has left.
     *
     * @return The string representation of tasks left.
     */
    public String tasksLeft() {
        return "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Adds a task to the array list.
     *
     * @param task The task that is being added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the task at the particular index.
     *
     * @param index The index of the task.
     * @return Return the Task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

}
