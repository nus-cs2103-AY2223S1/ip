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
     * Gets a list of all the tasks from the task list
     * @return ArrayList of Task stored.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets a task at a specified index from the task list
     * @param index index number specifying the location of the task to be retrieved in the TaskList.
     * @return Task object located at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a new task into the task list
     * @param task Task object to be added to the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task at a specified index from the task list
     * @param index index number specifying the location of the task to be deleted in the TaskList.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Gets size of the task list
     * @return Integer size of the TaskList.
     */
    public int getSize() {
        return this.tasks.size();
    }
}