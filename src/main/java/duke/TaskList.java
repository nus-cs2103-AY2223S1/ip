package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * TaskList Default Constructor
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * TaskList Constructor if tasks are provided
     * @param tasks ArrayList of Task loaded.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Tasks Getter
     * @return ArrayList of Task stored.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Single Task Getter
     * @param index index number specifying the location of the task to be retrieved in the TaskList.
     * @return Task object located at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Task Adder
     * @param task Task object to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Task Cleaner
     * @param index index number specifying the location of the task to be deleted in the TaskList.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Size Getter
     * @return Integer size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }
}