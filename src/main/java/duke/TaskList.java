package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a TaskList class that is responsible for managing the tasks.
 * 
 * @author Ramanathan Kumarappan
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Add Task to the TaskList.
     * @param t - The Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Retrieves the specified Task from TaskList.
     * @param i - An int specifying the index of the Task in the TaskList.
     * @return The Task at the given index.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Removes the specified Task from the TaskList.
     * @param i - An int specifying the index of the Task in the TaskList.
     */
    public void removeTask(int i) {
        this.tasks.remove(i);
    }

    /**
     * Gets the length of the TaskList (i.e the number of Tasks in the TaskList).
     * @return The number of Tasks in the TaskList.
     */
    public int getTaskListSize() {
        return this.tasks.size();
    }

    /**
     * Lists the Tasks in the TaskList.
     * @return A String representing the Tasks in the TaskList.
     */
    public String listTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(" ").append(tasks.get(i)).append(i != tasks.size() - 1 ? "\n" : "");
        }
        return list.toString();
    }
}
