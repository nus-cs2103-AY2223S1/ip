package uwu.task;

import java.util.ArrayList;

/** This class encapsulates a TaskList object. */
public class TaskList {
    /** The list of user's tasks. */
    public ArrayList<Task> userToDoList;

    /**
     * Constructor for TaskList object.
     */
    public TaskList() {
        this.userToDoList = new ArrayList<Task>();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return userToDoList.size();
    }

    /**
     * Returns the task at index i of the task list.
     *
     * @param i The index of the task to be returned.
     * @return The task at index i of the task list
     */
    public Task get(int i) {
        return userToDoList.get(i);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param newTask The new task added.
     */
    public void add(Task newTask) {
        userToDoList.add(newTask);
    }

    /**
     * Removes the task at the specified position in task list.
     *
     * @param i The index of the task to be removed.
     * @return The task that is removed.
     */
    public Task remove(int i) {
        return userToDoList.remove(i);
    }

    /**
     * Returns the string representation of the task list displayed
     * to the user.
     *
     * @return The string representation of a TaskList.
     */
    public String taskListToString() {
        int count = userToDoList.size();

        if (count == 0) {
            return "\n\n\tyou currently have no tasks, feed me <:";
        }

        String userToDoStr = "";

        for (int i = 0; i < count; i++) {
            String listItem = "\t" + String.valueOf(i + 1) + ".\t" + userToDoList.get(i).toString();

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
        int count = userToDoList.size();
        String taskListStorage = "";

        for (int i = 0; i < count; i++) {
            String taskItem = userToDoList.get(i).toStorageString();

            if (i == 0) {
                taskListStorage = taskItem;
            } else {
                taskListStorage = taskListStorage + "\n" + taskItem;
            }
        }

        return taskListStorage;
    }
}
