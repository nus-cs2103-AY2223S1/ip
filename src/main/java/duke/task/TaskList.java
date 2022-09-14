package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

import duke.exceptions.DukeException;

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

    public Task getTask(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Oops! Index is out of range!");
        }
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

    public void remove(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Oops! Index is out of range!");
        }
        list.remove(index);
    }

    /**
     * Marks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void mark(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Oops! Index is out of range!");
        }
        list.get(index).mark();
    }

    /**
     * Unmarks a task in the <code>TaskList</code>.
     *
     * @param index the index of the task.
     */
    public void unmark(int index) throws DukeException {
        if (index < 0 || index >= list.size()) {
            throw new DukeException("Oops! Index is out of range!");
        }
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
     * Converts <code>TaskList</code> to a string that is formatted to be written to <code>Storage</code>.
     *
     * @return the formatted string.
     */
    public String toStorageString() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.list) {
            data.append(task.toString());
            data.append("\n");
        }
        return data.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(list.get(i));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
