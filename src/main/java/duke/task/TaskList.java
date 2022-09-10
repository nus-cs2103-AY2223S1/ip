package duke.task;

import java.util.ArrayList;

/**
 * Represents a task list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new instance of an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of a task list with some tasks.
     *
     * @param tasks The initial tasks in the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves the task at a given index in the task list.
     *
     * @param i The index of a task in the task list.
     * @return The task at the given index.
     */
    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add to the task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed from the task list.
     */
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    public ArrayList<Task> findTask(String query) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.containsQuery(query)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
