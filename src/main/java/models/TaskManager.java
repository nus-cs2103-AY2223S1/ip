package models;

import database.TaskDatabase;
import exceptions.DukeException;

import java.util.List;

/**
 * Utility class to manage the list of tasks and provides augmenting operations to
 * add to the list of tasks or to enumerate through the list of tasks
 *
 * @author Emily Ong Hui Qi
 */
public class TaskManager {
    // The list of tasks, accessed using 0-based indices
    private static final String NO_TASKS_AVAILABLE = "There are currently no tasks available. Add one now!";
    private static final String TASK_LIST_STATUS_MESSAGE = "Now you have %s task(s) in the list.";
    private static final String TASK_LIST_CANNOT_READ_STATUS_MESSAGE = "Uh oh, I cannot read the tasks in the list!";

    private final TaskDatabase taskDatabase;

    public TaskManager(TaskDatabase taskDatabase) {
        this.taskDatabase = taskDatabase;
    }

    /**
     * Adds the received task into the task list
     *
     * @param task Task received from the caller
     */
    public Task add(Task task) throws DukeException {
        return this.taskDatabase.addTask(task);
    }

    /**
     * Updates the specified task corresponding to the given task index
     * @param taskNumber The 1-based task number, possibly corresponding to a particular task
     * @param task the task to be updated
     * @return The updated task
     * @throws DukeException If the task cannot be updated
     */
    public Task update(int taskNumber, Task task) throws DukeException {
        return this.taskDatabase.updateTask(taskNumber - 1, task);
    }

    /**
     * Deletes the specified task number (1-index) from the task list
     *
     * @param taskNumber The 1-based task number, possibly corresponding to a particular task
     * @return The deleted task
     */
    public Task delete(int taskNumber) throws DukeException {
        return this.taskDatabase.deleteTask(taskNumber - 1);
    }

    /**
     * Retrieve the task
     *
     * @param taskNumber An index (1-index) corresponding to a particular task
     * @return Task corresponding to the particular task number
     */
    public Task get(int taskNumber) throws DukeException {
        return this.taskDatabase.findTask(taskNumber - 1);
    }

    /**
     * Returns the number of tasks in the task manager list
     *
     * @return Number of tasks in the task manager list
     */
    private int count() throws DukeException {
        return this.taskDatabase.count();
    }

    /**
     * Returns the status of the task manager on the number of tasks in the list
     * @return Status of the task manager
     */
    public String getStatus() {
        try {
            return String.format(TaskManager.TASK_LIST_STATUS_MESSAGE, this.count());
        } catch (DukeException e) {
            return String.format("%s: %s", TaskManager.TASK_LIST_CANNOT_READ_STATUS_MESSAGE, e.getMessage());
        }
    }

    @Override
    public String toString() {
        List<Task> allTasks;
        try {
            allTasks = this.taskDatabase.readAllTasks();
            if (allTasks.size() == 0) {
                return TaskManager.NO_TASKS_AVAILABLE;
            }
        } catch (DukeException e) {
            return TaskManager.NO_TASKS_AVAILABLE;
        }

        StringBuilder taskManagerDisplay = new StringBuilder();
        for (int i = 0; i < allTasks.size(); i++) {
            // Implicitly invoke the display of the task defined in the Task class
            taskManagerDisplay.append(String.format("%d. %s\n", i + 1, allTasks.get(i)));
        }
        return taskManagerDisplay.toString();
    }
}
