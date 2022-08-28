package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores list of tasks and related commands.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs TaskList with given list of tasks.
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Obtains list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Marks desired task as done.
     * @param taskNumber Task number.
     */
    public String markAsDone(int taskNumber) {
        return taskList.get(taskNumber - 1).markAsDone();
    }

    /**
     * Marks desired task as not done.
     * @param taskNumber Task number.
     */
    public String markNotDone(int taskNumber) {
        return taskList.get(taskNumber - 1).markNotDone();
    }

    /**
     * Removes desired task from list.
     * @param taskNumber Task number.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Obtains relevant task from list.
     * @param taskNumber Task number.
     * @return required task.
     */
    public Task retrieveTask(int taskNumber) {
        return this.taskList.get(taskNumber - 1);
    }

    /**
     * Obtains size of the list.
     * @return Size of list.
     */
    public int getListSize() {
        return taskList.size();
    }

    /**
     * Adds task to the list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public ArrayList<Task> searchMatches(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Task task: taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingList.add(task);
            }
        }
        return matchingList;
    }
}
