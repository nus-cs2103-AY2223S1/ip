package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents the list of Tasks in the Duke program.
 *
 * @author ish1506
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> list;

    /**
     * Constructs a <code>TaskList</code> given an <code>ArrayList</code> of <code>Task</code> instances.
     *
     * @param list the list of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Adds a <code>Task</code> to the end of the <code>TaskList</code>.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Matks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void mark(int index) {
        list.get(index).mark();
    }

    /**
     * Unmarks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void unmark(int index) {
        list.get(index).unmark();
    }

    /**
     * Returns true if this <code>TaskList</code> contains no <code>Task</code>s.
     *
     * @return returns true if this <code>TaskList</code> contains no <code>Task</code>s.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Finds all <code>Task</code>s which match a given keyword.
     *
     * @param keyword the keyword to match.
     * @return a new <code>TaskList</code> containing all matching <code>Task</code>s.
     */
    public TaskList find(String keyword) {
        TaskList newList = new TaskList(new ArrayList<>());
        for (Task task : list) {
            if (task.isNameContaining(keyword)) {
                newList.add(task);
            }
        }
        return newList;
    }

    /**
     * Prints the tasks in the list, with their indices starting from 1.
     */
    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    /**
     * Converts <code>TaskList</code> to a string that is formatted to be written to <code>Storage</code>.
     *
     * @return the formatted string.
     */
    public String toStorageString() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.list) {
            data.append(task.toString() + "\n");
        }
        return data.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
