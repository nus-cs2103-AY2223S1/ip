package duke.tasks;

import java.util.ArrayList;

/**
 * TaskList contains all the tasks that user has created
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor of empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of TaskList based on previous tasks stored in data
     * @param tasks ArrayList of tasks of previous tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds task to TaskList
     * @param task Task to be added
     */
    public void add(Task task) {
        this.tasks.add(task);

    }

    /**
     * Returns number of tasks in TaskList
     * @return Number of tasks in TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Removes task from TaskList
     * @param index Index of task to be removed
     * @return The task that is being removed
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets task from TaskList
     * @param index Index of task to be gotten
     * @return The task to be gotten
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Returns ArrayList of tasks of all the tasks
     * @return ArrayList of tasks of all the tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

}
