package tasks;

import exception.LunaException;
import java.util.ArrayList;

/**
 * Represents a list of tasks saved by the user.
 *
 * @author fannyjian
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Initialises the task list with an already saved list of tasks.
     *
     * @param tasks Saved tasks to be added to this instance.
     * @throws LunaException
     */
    public TaskList(ArrayList<Task> tasks) throws LunaException {
        if (tasks == null) {
            throw new LunaException("Luna did not find any tasks ⛈");
        }
        this.tasks = tasks;
    }

    /**
     * Initialises the task list without any saved tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Indicates the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds the specified Task to the TaskList.
     *
     * @param task New Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the specified Task from the TaskList
     *
     * @param num Index of Task in TaskList to be removed
     * @return Deleted task.
     */
    public Task delete(int num) {
        Task removed = this.tasks.get(num);
        this.tasks.remove(num);
        return removed;
    }

    /**
     * Marks the specified Task as completed.
     *
     * @param num Index of Task in TaskList to be updated.
     * @return Task updated.
     */
    public Task mark(int num) {
        this.tasks.get(num).setStatusIcon(true);
        return this.tasks.get(num);
    }

    /**
     * Marks the specified Task as uncompleted.
     *
     * @param num Index of Task in TaskList to be updated.
     * @return Task updated.
     */
    public Task unmark(int num) {
        this.tasks.get(num).setStatusIcon(false);
        return this.tasks.get(num);
    }

    /**
     * Provides the string representation of TaskList to be written to the hard disk.
     *
     * @return String representation of TaskList
     */
    public String stored() {
        String ls = "";
        for(int i = 0; i < tasks.size(); i++) {
            ls += "\n       " + tasks.get(i);
        }
        return ls;
    }

    /**
     * Returns the string representation of the TaskList with indices.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        String list = "";
        for(int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                list += i + 1 + ". " + tasks.get(i);
            } else {
                list += i + 1 + ". " + tasks.get(i) + "\n";
            }
        }
        return list;
    }
}
