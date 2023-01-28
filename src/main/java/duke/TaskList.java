package duke;

import java.util.ArrayList;

/**
 * TaskList class uses an ArrayList to represent the list of tasks stored.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class
     *
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the list of tasks as an ArrayList
     *
     * @return ArrayList list of tasks
     */

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Retrieves the i-th task in the list
     *
     * @param i task number
     * @return Task task
     */

    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the list
     *
     * @param task Task
     */

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list
     *
     * @param task Task
     *
     */

    public void delete(Task task) {
        tasks.remove(task);
    }

    /**
     * Updates the description of a task
     *
     * @param taskNo task number
     * @param updatedField String
     */

    public void updateTaskDescription(int taskNo, String updatedField) {
        Task task = tasks.get(taskNo);
        task.updateDescription(updatedField);
    }


}
