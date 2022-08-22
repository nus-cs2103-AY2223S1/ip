package duke;

import java.util.ArrayList;

/**
 * Contains a list of tasks.
 *
 * @author Lai Han Wen
 */
public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the current list of tasks.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Deletes a task from the current list of tasks.
     *
     * @param i Number of task to be deleted.
     */
    public void delete(int i) {
        this.list.remove(i - 1);
    }

    /**
     * Counts the number of tasks in the current list of tasks.
     *
     * @return Number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int i) {
        return this.list.get(i - 1);
    }
}
