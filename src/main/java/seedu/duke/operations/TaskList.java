package seedu.duke.operations;

import seedu.duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList holds a list of tasks that the user has saved so
 * far.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Creates a new TaskList from an ArrayList of Task.
     *
     * @param tasks     ArrayList of Task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    /**
     * Indicate the amount of tasks within this TaskList.
     *
     * @return      Amount of tasks in TaskList
     */
    public int numOfTasks() {
        return taskList.size();
    }

    /**
     * Return the task with the given index (1-indexed as per
     * what the user will see in command line.
     *
     * @param taskIndex     int index
     * @return              Task
     */
    public Task fetchTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }

    /**
     * Indicate if the current TaskList is empty.
     *
     * @return  true if TaskList is empty, false otherwise
     */
    public boolean isEmpty() {
        return numOfTasks() == 0;
    }

    /**
     * Removes a Task from TaskList given its index.
     *
     * @param index     int index
     * @return          Removed Task
     */
    public Task removeTask(int index) {
        return taskList.remove(index);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task      Task to be added to TaskList
     */
    public void addTask(Task task) {
        taskList.add(task);
    }
}
