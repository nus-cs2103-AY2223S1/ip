package duke.task;

import java.util.ArrayList;

/**
 * Represents the list of Tasks in the Duke program.
 *
 * @author ish1506
 */
public class TaskList {
    private final ArrayList<Task> list;

    /**
     * Constructs a <code>TaskList</code> given an <code>ArrayList</code> of <code>Task</code> instances.
     *
     * @param list the list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets a <code>Task</code> in the <code>TaskList</code>.
     *
     * @param index the index of the task to retrieve.
     */

    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Adds a <code>Task</code> to the end of the <code>TaskList</code>.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * Matks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void mark(int index) {
        this.list.get(index).mark();
    }

    /**
     * Unmarks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void unmark(int index) {
        this.list.get(index).unmark();
    }

    /**
     * Prints the tasks in the list, with their indices starting from 1.
     */
    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }
}
