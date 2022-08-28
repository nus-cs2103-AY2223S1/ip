package duke;

import java.util.ArrayList;

/**
 * A task list handler that adds/deletes tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int getCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Appends task to the task list.
     * @param t Task to append.
     * @return The modified task list.
     */
    public ArrayList<Task> addTask(Task t) {
        tasks.add(t);
        return tasks;
    }

    /**
     * Removes task at a specified index in the task list.
     *
     * @param index Index of the task to be removed.
     * @return The modified task list.
     */
    public Task deleteTask(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    /**
     * Marks or unmarks the given task depending on the boolean supplied.
     *
     * @param t The task to mark or unmark.
     * @param b Boolean that indicates whether or not to mark the task.
     * @return The modified task list.
     */
    public ArrayList<Task> markTask(Task t, boolean b) {
        t.setMarked(b);
        return tasks;
    }
}
