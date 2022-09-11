package uwu.task;

import java.util.ArrayList;

/** This class encapsulates a TaskList object. */
public class TaskList {
    /** The list of user's tasks. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at index i of the task list.
     *
     * @param i The index of the task to be returned.
     * @return The task at index i of the task list
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Sets the userToDoList to tasks.
     *
     * @param tasks The updated task list.
     */
    public void setTaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The new task added.
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes the task at the specified position in task list.
     *
     * @param i The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task remove(int i) {
        return tasks.remove(i);
    }

    /**
     * Returns the string representation of the task list displayed
     * to the user.
     *
     * @return The string representation of a TaskList.
     */
    public String taskListToString() {
        int count = tasks.size();

        if (count == 0) {
            return "you currently have no tasks, feed me <:";
        }

        String userToDoStr = "";

        for (int i = 0; i < count; i++) {
            String listItem = " " + String.valueOf(i + 1) + ". " + tasks.get(i).toString();

            userToDoStr = userToDoStr + "\n" + listItem;
        }

        return userToDoStr;
    }

    /**
     * Returns the TaskList represented by a string to be stored in the taskList
     * file in user's hard disk.
     *
     * @return The string representation of stored TaskList.
     */
    public String taskListToStorageString() {
        int count = tasks.size();
        String taskListStorage = "";

        for (int i = 0; i < count; i++) {
            String taskItem = tasks.get(i).toStorageString();

            if (i == 0) {
                taskListStorage = taskItem;
            } else {
                taskListStorage = taskListStorage + "\n" + taskItem;
            }
        }

        return taskListStorage;
    }
}
