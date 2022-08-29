package duke;

import duke.tasks.Task;

import java.util.ArrayList;


/**
 * Deals with keeping track of Tasks added.
 */
public class TaskList {
    protected static ArrayList<Task> taskList = new ArrayList<Task>();
    private Ui ui;
    private Storage storage;

    /**
     * TaskList constructor.
     *
     * @param taskList ArrayList to store Tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Default TaskList constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds Task on Duke program and ensure Task is added to memory.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Returns TaskList size.
     *
     * @return TaskList size.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Changes task status.
     *
     * @param taskNumber Task to change status.
     * @param isDone status to change to.
     */
    public void setTaskStatus(int taskNumber, boolean isDone) {
        Task currentTask = taskList.get(taskNumber);
        currentTask.setTaskStatus(isDone);
    }

    /**
     * Returns the String format of a Task.
     *
     * @param taskNumber Task to turn into String.
     * @return String of task specified.
     */
    public String getTaskToString(int taskNumber) {
        Task currentTask = taskList.get(taskNumber);
        return currentTask.toString();
    }

    /**
     * Deletes task from list.
     *
     * @param taskNumber The row of Task to be deleted.
     */
    public String deleteTask(int taskNumber) {
        Task removedTask = taskList.remove(taskNumber);
        return removedTask.toString();
    }

    /**
     * Prints list of Tasks being tracked.
     *
     * @return String of Tasks in list.
     */
    public String listToString() {
        String message = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            message += ("     "
                    + i
                    + "."
                    + taskDescription);
            if (i != taskList.size()) {
                message += "\n";
            }
        }
        return message;
    }

    /**
     * Displays Tasks that matches what user searched for.
     *
     * @param keyword User input for searching Tasks.
     */
    public String findTask(String keyword) {
        String message = "";
        for (int i = 1; i <= taskList.size(); i++) {
            Task currentTask = taskList.get(i - 1);
            String taskDescription = currentTask.toString();
            if (taskDescription.contains(keyword)) {
                message += ("     "
                        + i
                        + "."
                        + taskDescription);
                if (i != taskList.size()) {
                    message += "\n";
                }
            }
        }
        return message;
    }
}
