package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * The TaskList class encapsulates a list used to store tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Appends a task to the end of the list.
     *
     * @param t Task to be appended.
     */
    public void append(Task t) {
        this.taskList.add(t);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param i Index of list where a task is to be removed.
     */
    public void remove(int i) {
        this.taskList.remove(i);
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return number of tasks in the list.
     */
    public int length() {
        return this.taskList.size();
    }

    /**
     * Returns task at specified index.
     *
     * @param i Index of list where a task is to be retrieved.
     * @return Task at specified index.
     */
    public Task index(int i) {
        return this.taskList.get(i);
    }

    /**
     * Check if a specified task already exists in the tasklist.
     *
     * @param task Task to be checked.
     * @return Whether specified task exists in the tasklist.
     */
    public boolean detectDuplicate(Task task) {
        for (int i = 0; i < this.taskList.size(); i++) {
            if (task.equals(taskList.get(i))) {
                return true;
            }
        }
        return false;
    }
}
