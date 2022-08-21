package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * The object Duke uses to keep track of the user's input tasks
 * @author Nephelite
 * @version 0.1
 */
public class TaskList {
    /**
     * The ArrayList of Tasks that Duke uses to track the user's input tasks
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object, if there was a prior saved ArrayList
     * @param taskList
     * @since 0.1
     */
    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Constructor for a TaskList object, if there was no prior saved ArrayList
     * @since 0.1
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Method to obtain the size of taskList
     * @return the size of taskList
     * @since 0.1
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Method to retrieve the task of an index from taskList
     * @param taskId index of the desired task
     * @return the task at index in taskList
     * @since 0.1
     */
    public Task getTask(int taskId) {
        return taskList.get(taskId);
    }

    /**
     * Method to reemove the task of an index from taskList
     * @param taskId index of the desired task
     * @since 0.1
     */
    public void remove(int taskId) {
        tasks.remove(taskId);
    }

    /**
     * Method to add a task to taskList
     * @param task Task to insert at the end of taskList
     * @since 0.1
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Method to print out the string representations of all tasks in taskList,
     * with a 1-indexed numbering system.
     * @since 0.1
     */
    public void printList() {
        if (tasks == null || tasks.size() <= 0) {
            System.out.println("No tasks assigned yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + getTask(i));
            }
        }
    }
}
