package duke;
import java.util.ArrayList;

/**
 * Data structure to keep track of user's tasks
 */
public class TaskList {
    private ArrayList<Task> tasks;
    /**
     * Constructor to create a new TaskList given an arrayList of tasks
     * @param tasks ArrayList of Task objects
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Constructor to create an empty TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * functional method to add a Task
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * functional method to delete a task
     * @param index Index at which to delete the task at
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * functional method to retrieve a specific Task
     * @param index Index where the Task is stored
     * @return the Task stored at the index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all Tasks stored in the data structure
     * @return an ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of Tasks currently stored
     * @return number of Tasks
     */
    public int getSize() {
        return tasks.size();
    }
}
