package duke;
import java.util.ArrayList;

/**
 * The TaskList class encapsulates a collection of Task objects.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds the task given into the list.
     *
     * @param task Task to be done.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task of the given index in the list.
     *
     * @param index The index of the task to return.
     * @return the task of given index found in list.
     */
    public Task get(int index) {
        Task t = tasks.get(index);
        return t;
    }

    /**
     * Removes the task of the given index in the list.
     *
     * @param index The index of the task to be removed
     * @return The element that was removed from the list
     */
    public Task remove(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the list.
     *
     * @return the collection of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
