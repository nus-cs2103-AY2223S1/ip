package duke.util;

import java.util.ArrayList;

import duke.Response;
import duke.task.Task;

/**
 * Class that contains the task list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for a new Task list.
     *
     * @param taskList the task list
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Creates a new task.
     *
     * @param task the task
     */
    public void createTask(Task task, Response response) {
        tasks.add(task);
        String message = "Got it. I've added this task: \n"
                + task.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
        response.append(message);
    }

    /**
     * Creates a new task silently.
     *
     * @param task the task
     */
    public void createTaskSilently(Task task) {
        tasks.add(task);
    }

    /**
     * Delete task.
     *
     * @param index the index
     */
    public void deleteTask(int index, Response response) {
        String message = "Done! I have deleted this task:\n  " + tasks.get(index)
                + "\nNow you have " + (tasks.size() - 1) + " tasks in the list.";
        tasks.remove(index);
        response.append(message);
    }

    /**
     * Gets size of task list.
     *
     * @return the size
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets task from a given index.
     *
     * @param i the
     * @return the task
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Gets task list.
     *
     * @return the task list
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
