package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list of tasks that are stored by Duke.
 */
public class TaskList {

    protected List<Task> tasks = new ArrayList<>();

    /**
     * Constructor for a TaskList.
     *
     * @param tasks the list of tasks to be added to the tasklist.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructor for an empty TaskList.
     */
    public TaskList() {
    }

    /**
     * Adds a particular task to the TaskList.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a particular task form the TaskList.
     *
     * @param taskNumber the task's number to be deleted in the list.
     * @return The task that is to be deleted.
     */
    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    /**
     * Marks a particular task in the TaskList as done.
     *
     * @param taskNumber the task's number to be deleted in the list.
     */
    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks a particular task in the TaskList as not done.
     *
     * @param taskNumber the task's number to be deleted in the list.
     */
    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    /**
     * Retrieves the list of tasks stored in the TaskList.
     *
     * @return a List of Tasks stored in the TaskList.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean checkDuplicates(Task task) {
        for (Task t : this.tasks) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }

}
