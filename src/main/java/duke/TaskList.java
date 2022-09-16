package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Predicate;

/**
 * A class encapsulating an ArrayList\<Task\> as a task list.
 */
public class TaskList {
    public enum Categories {
        DEADLINE,
        EVENT,
        TODO
    }

    private ArrayList<Task> list = new ArrayList<>();

    /**
     * Add a task to the task list.
     *
     * @param t task to be added.
     */
    public void add(Task t) {
        assert t != null : "task is not null";
        list.add(t);
    }

    public boolean contains(Task t) {
        return list.contains(t);
    }
    /**
     * Get a task from the task list via its index.
     *
     * @param i index to retrive task from.
     * @return a Task object.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Remove a task from the task list via its index.
     *
     * @param i index of task to remove.
     */
    public void remove(int i) {
        list.remove(i);
    }

    /**
     * Get the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Replace the current task list with a new list.
     *
     * @param list an arraylist of tasks.
     */
    public void loadTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Replace the current task list with the contents of an array of tasks.
     *
     * @param list an array of Task objects.
     */
    public void loadTaskList(Task[] list) {
        this.list = new ArrayList<>();
        for (Task t : list) {
            this.list.add(t);
        }
    }

    /**
     * Enumerates the Task List into a String
     *
     * @return a string enumerating all tasks in list.
     */
    public String toString() {
        String returnMsg = "";
        int index = 1;

        for (Task t : this.list) {
            returnMsg += index + ". " + t + "\n";
            index++;
        }

        return returnMsg;
    }

    /**
     * Find the index of a task in the list.
     * @param t task to find
     * @return index if found, else return -1.
     */
    public int indexOf(Task t) {
        return this.list.indexOf(t);
    }
    /**
     * Replace the current task list with the saved list of tasks from storage.
     *
     * @param storage a Storage object to load the data from.
     */
    public void loadFromLocalStorage(LocalStorage storage) {
        loadTaskList(storage.load());
    }

    /**
     * Return an iterator of the list of tasks.
     * Very useful for iterating over the list of tasks.
     *
     * @return an Iterator object.
     */
    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

    public TaskList filter(Predicate<Task> p) {
        TaskList newList = new TaskList();
        for (Task t : this.list) {
            if (p.test(t)) {
                newList.add(t);
            }
        }
        return newList;
    }

    public TaskList filterByCategory(Categories c) {
        return this.filter((t) -> {
            return t.isCategory(c);
        });
    }

}
