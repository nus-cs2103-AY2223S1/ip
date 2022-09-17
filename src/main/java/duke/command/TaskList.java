package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Stores list of tasks and related commands.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

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

    public String markHighPriority(int taskNumber) {
        String output = taskList.get(taskNumber - 1).markAsPriority();
        taskList.sort((t1, t2) -> {
            if (t1.checkPriority() && t2.checkPriority()) {
                return 0;
            } else if (t1.checkPriority() && !t2.checkPriority()) {
                return -1;
            } else {
                return 1;
            }
        });
        return output;
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

    public ArrayList<Task> searchTasks(String keyword) {
        ArrayList<Task> matchingList = new ArrayList<>();
        for (Task task : taskList) {
            String description = task.getDescription();
            if (description.contains(keyword)) {
                matchingList.add(task);
            }
        }
        return matchingList;
    }

}
