package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulation of the list of Tasks stored in the chatbot.
 *
 * @author   Sun Ruoxin
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    protected ArrayList<Task> list;

    /**
     * Class constructor with specific list to be encapsulated.
     *
     * @param list the list of tasks to be encapsulated
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Class constructor to encapsulate an empty list.
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Returns the number of tasks stored in the list.
     *
     * @return the number of tasks stored in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task stored in the list corresponding to the index.
     *
     * @param index the index of the task in the list
     * @return the task corresponding to the index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds the task to the list.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes the task corresponding to the index from the list.
     *
     * @param index the index of the task to be removed
     */
    public void remove(int index) {
        list.remove(index);
    }
}
