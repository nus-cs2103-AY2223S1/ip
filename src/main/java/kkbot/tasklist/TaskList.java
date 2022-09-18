package kkbot.tasklist;

import java.util.ArrayList;

import kkbot.tasklist.exceptions.InvalidTaskException;
import kkbot.tasks.Task;

/**
 * TaskList class to assist kkbot.kkbot in storing tasks as a list.
 * Uses an arrayList and stores a list of Task objects.
 *
 * @author AkkFiros
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList
     *
     * @param tasks a list of tasks that kkbot.kkbot takes in
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Alternative constructor for TaskList when previous task list
     * can't be loaded OR when there is no previous task list to load.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Method to retrieve the number of tasks stored by kkbot.kkbot
     *
     * @return the number of tasks stored.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Method to return the task given its index
     * @param index the user specified index
     * @return the task associated with that index
     * @throws InvalidTaskException if user input is wrong
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    /**
     * Method to add a task to the end of the taskList
     * @param task the task to be added
     * @return the task that was just added
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     *
     * @param index
     * @return the task that was just deleted
     * @throws InvalidTaskException if user input is wrong
     */
    public Task deleteTask(int index) throws InvalidTaskException{
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException();
        }
        Task toDelete = tasks.get(index);
        tasks.remove(toDelete);
        return toDelete;
    }

    /**
     * Method to change the status of a specified task in the taskList
     * @param index the specific index number of the task
     *              to change its status
     * @param status the new completion status of the task
     * @return the task that had it status changed
     * @throws InvalidTaskException if user input is wrong
     */
    public Task changeTaskStatus(int index, boolean status) throws InvalidTaskException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskException();
        }

        Task task = tasks.get(index);
        task.changeStatus(status);
        return task;
    }
}
