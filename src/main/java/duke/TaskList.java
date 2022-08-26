package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Encapsulation of the list of Tasks stored in the chatbot.
 *
 * @author   Sun Ruoxin
 * @version  %I%, %G%
 */
public class TaskList {
    /**
     * The list of Tasks.
     */
    protected ArrayList<Task> list;

    /**
     * Class constructor with specific list to be encapsulated.
     *
     * @param list  the list of Tasks to be encapsulated
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
     * Returns the number of Tasks stored in the list.
     *
     * @return  the number of Tasks stored in the list
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the Task stored in the list corresponding to the index.
     *
     * @param index  the index of the Task in the list
     * @return       the Task corresponding to the index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Adds the Task to the list.
     *
     * @param task  the Task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Removes the Task corresponding to the index from the list.
     *
     * @param index  the index of the Task to be removed
     */
    public void remove(int index) {
        list.remove(index);
    }
}
