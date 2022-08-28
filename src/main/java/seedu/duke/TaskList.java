package seedu.duke;

import java.util.ArrayList;

/**
 * Represents a TaskList, store, add and delete <code>Task</code> objects
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates a new TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the number of Tasks in this TaskList
     *
     * @return number of Tasks in this TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a given Task to this TaskList and returns the Task
     *
     * @param t Task to be added to this TaskList
     * @return Task to be added to this TaskList
     */
    public Task add(Task t) {
        this.tasks.add(t);
        return t;
    }

    /**
     * Removes a Task from this TaskList at a given Index
     *
     * @param i index of Task to be removed
     */
    public void delete(int i) {
        this.tasks.remove(i-1);
    }

    /**
     * Returns the Task at the specified position in this TaskList.
     *
     * @param i index of the Task to return
     * @return the Task at the specified position in this TaskList
     */
    public Task get(int i) {
        return this.tasks.get(i-1);
    }

    /**
     * Returns an ArrayList containing all Tasks in this TaskList
     *
     * @return an ArrayList containing all Tasks in this TaskList
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks the Task at the specified position in this TaskList and return this Task.
     *
     * @param i index of the Task to mark
     * @return the Task at the specified position in this TaskList
     */
    public Task mark(int i) {
        Task task = tasks.get(i-1);
        task.setStatusIcon(true);
        return task;
    }

    /**
     * Unmarks the Task at the specified position in this TaskList and return this Task.
     *
     * @param i index of the Task to unmark
     * @return the Task at the specified position in this TaskList
     */
    public Task unmark(int i) {
        Task task = tasks.get(i-1);
        task.setStatusIcon(false);
        return task;
    }

    /**
     * Filters the Tasks in this TaskList and return an ArrayList containing Tasks
     * containing the specified substring
     *
     * @param s substring to be compared to the description of Tasks
     * @return an ArrayList containing filtered Tasks
     */
    public ArrayList<Task> filter(String s) {
        ArrayList<Task> result = new ArrayList<Task>();
        for (Task task : this.tasks) {
            if (task.description.contains(s)) {
                result.add(task);
            }
        }
        return result;
    }
}
