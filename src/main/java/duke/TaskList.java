package duke;

import java.util.ArrayList;
import java.util.HashMap;

import duke.tasks.Task;

/**
 * Deals with keeping track of Tasks added.
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    protected HashMap<String, Task> taskListQuickFind;

    /**
     * TaskList constructor.
     *
     * @param taskList ArrayList to store Tasks.
     */
    public TaskList(ArrayList<Task> taskList, HashMap<String, Task> taskListQuickFind) {
        this.taskList = taskList;
        this.taskListQuickFind = taskListQuickFind;
    }

    /**
     * Default TaskList constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskListQuickFind = new HashMap<>();
    }

    /**
     * Adds Task on duke.Duke program and ensure Task is added to memory.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task, String taskDescription) {
        boolean successful = taskList.add(task);
        Object taskListQuickFindPutReturnValue = taskListQuickFind.put(taskDescription, task);

        assert successful : "Task should be added successfully";
        assert taskListQuickFindPutReturnValue == null : "Task should be added to an empty mapping";
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
        assert currentTask != null : "Task should not be null";
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

        assert removedTask != null : "Task should not be null";

        String taskDescription = removedTask.getDescription();
        Task taskListQuickFindRemoveReturnValue = taskListQuickFind.remove(taskDescription);

        assert taskListQuickFindRemoveReturnValue != null : "Task associated with taskDescription should"
                + "not be null";

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
            assert currentTask != null : "Task should not be null";
            String taskDescription = currentTask.toString();
            message += (i
                    + ". "
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
            assert currentTask != null : "Task should not be null";
            String taskDescription = currentTask.toString();
            if (taskDescription.contains(keyword)) {
                message += (i
                        + ". "
                        + taskDescription);
                if (i != taskList.size()) {
                    message += "\n";
                }
            }
        }
        return message;
    }

    public boolean taskDescriptionExists(String taskDescription) {
        return taskListQuickFind.containsKey(taskDescription);
    }
}
