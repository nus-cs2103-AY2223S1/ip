package duke.task;

import java.util.ArrayList;

/**
 * The class is used to simulate the list used to store the Tasks added to the user's
 * to-do list and provide basic operations similar to a to-do list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Overloaded Constructor to create an instance of TaskList when provided with
     * a list of tasks in the case where there are existing data stored in a local
     * txt file.
     *
     * @param taskInStorage an ArrayList of tasks retrieved from a local txt file
     */
    public TaskList(ArrayList<Task> taskInStorage) {
        this.taskList = taskInStorage;
    }

    /**
     * Constructor to create an instance of TaskList when there are no data stored
     * locally.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * To add a task to the TaskList.
     *
     * @param task A task to be added into the list
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * To remove a task from the TaskList.
     *
     * @param taskNumber the specified task to be removed
     */
    public void delete(int taskNumber) {
        this.taskList.remove(taskNumber);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return an integer representing the number of tasks in the TaskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Getter function to retrieve specified task
     *
     * @param taskIndex the taskNumber to retrieve from the TaskList
     * @return a Task corresponding to the number that was specified
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Check if the TaskList is empty.
     *
     * @return true if the TaskList is empty, otherwise return false
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }
}
