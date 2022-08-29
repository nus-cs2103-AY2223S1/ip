package duke.task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public void mark(int index) {
        list.get(index).mark();
    }

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

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }
}
