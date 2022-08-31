package duke.util;

import java.util.ArrayList;

import duke.Response;
import duke.task.Task;

/**
 * Class that contains the task list
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for a new Task list.
     *
     * @param taskList the task list
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates a new task.
     *
     * @param task the task
     */
    public void createTask(Task task, Response response) {
        taskList.add(task);
        String message = "Got it. I've added this duke.task: \n"
                + task.toString() + "\nNow you have " + taskList.size() + " tasks in the list.";
        response.append(Ui.formatMessage(message));
    }

    /**
     * Creates a new task silently.
     *
     * @param task the task
     */
    public void createTaskSilently(Task task) {
        taskList.add(task);
    }

    /**
     * Delete task.
     *
     * @param index the index
     */
    public void deleteTask(int index, Response response) {
        String message = "Done! I have deleted this duke.task:\n  " + taskList.get(index)
                + "\nNow you have " + (taskList.size() - 1) + " tasks in the list.";
        taskList.remove(index);
        response.append(Ui.formatMessage(message));
    }

    public String markTask(int index) {
        return "";
    }

    public String unmarkTask(int index) {
        return "";
    }

    public String findTask() {
        return "";
    }

    /**
     * Gets size of task list.
     *
     * @return the size
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets task from a given index.
     *
     * @param i the
     * @return the task
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
