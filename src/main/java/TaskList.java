import java.util.LinkedList;
import java.util.List;

/**
 * A class to store the tasks in Duke.
 */
public class TaskList {

    /** List to store all tasks entered by the user. */
    private LinkedList<Task> lst;

    /**
     * Constructor for the TaskList class.
     */
    public TaskList() {
        this.lst = new LinkedList<>();
    }

    /**
     * Returns the list stored.
     *
     * @return The list stored.
     */
    public List<Task> getList() {
        return this.lst;
    }

    /**
     * Returns the size of the list stored.
     *
     * @return The size of the list stored.
     */
    public int size() {
        return lst.size();
    }

    /**
     * Returns the Task at index i.
     *
     * @param i The index of the Task to be returned.
     * @return The Task at index i.
     */
    public Task get(int i) {
        return lst.get(i);
    }

    /**
     * Returns the most recently added Task.
     *
     * @return The most recently added Task.
     */
    public Task getLast() {
        return lst.get(lst.size() - 1);
    }

    /**
     * Sets the Task at index i to be done.
     *
     * @param i The index of the Task to be marked done.
     * @return True if there was a change from undone to done, and false if the Task was already done.
     */
    public boolean setDone(int i) {
        return lst.get(i).setDone();
    }

    /**
     * Sets the Task at index i to be undone.
     *
     * @param i The index of the Task to be marked undone.
     * @return True if there was a change from done to undone, and false if the Task was already not done.
     */
    public boolean setUnDone(int i) {
        return lst.get(i).setUnDone();
    }

    /**
     * Removes the Task at index i.
     *
     * @param i The index of the Task to be removed.
     */
    public void remove(int i) {
        lst.remove(i);
    }

    /**
     * Adds the Task to the task list.
     *
     * @param task The Task to be added to the list.
     */
    public void add(Task task) {
        lst.add(task);
    }
}
