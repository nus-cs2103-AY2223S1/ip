package duke.task;

import java.util.ArrayList;

/**
 * Class that stores the list of tasks and methods to manipulate the tasks.
 */
public class TaskList {
    /** List of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructor to initialize list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to initialize list of tasks using a given task list.
     * 
     * @param tasks The given task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints all tasks in the list.
     */
    public void list() {
        this.tasks.forEach(t -> System.out.println("  " + (this.tasks.indexOf(t) + 1) + ". " + t));
    }

    /**
     * Adds a given task to the list.
     * 
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a given task from the list.
     * 
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a given task as complete.
     * 
     * @param index The index of the task to be marked as complete.
     */
    public void markTask(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Marks a given task as incomplete.
     *
     * @param index The index of the task to be marked as incomplete.
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Converts the task list to its saved format.
     * @return The string representation of the saved format of the task list.
     */
    public String toSaveFormat() {
        StringBuilder sb = new StringBuilder();
        this.tasks.forEach(t -> sb.append(t.toSaveFormat()).append("\n"));
        return sb.toString();
    }

    /**
     * Gets the size of the task list.
     * 
     * @return The size of the task list.
     */
    public int getSize() {
        return this.tasks.size();
    } 
}
