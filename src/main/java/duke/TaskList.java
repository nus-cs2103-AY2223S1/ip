package duke;
import java.util.ArrayList;

/**
 * The TaskList class encapsulates a collection of Task objects.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task given into the list.
     *
     * @param task Task to be done.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Returns the task of the given index in the list.
     *
     * @param index The index of the task to return.
     * @return the task of given index found in list.
     */
    public Task get(int index) {
        Task t = taskList.get(index);
        return t;
    }

    /**
     * Removes the task of the given index in the list.
     *
     * @param index The index of the task to be removed
     * @return The element that was removed from the list
     */
    public Task remove(int index) {
        Task t = taskList.remove(index);
        return t;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the list.
     *
     * @return the collection of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
