package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList is the list that contains the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    /**
     * Constructor for TaskList, if no past data exists.
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.size = 0;
    }

    /**
     * Constructor for TaskList, if past data exists.
     *
     * @param tasks The ArrayList that contains saved tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(100);
        this.tasks.addAll(tasks);
        this.size = tasks.size();
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return Size of TaskList.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Adds a task to the current TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(this.tasks.size(), task);
        this.size++;
    }

    /**
     * Returns task of index i from TaskList.
     *
     * @param i The index of the task.
     * @return The task corresponding to the index.
     */
    public Task get(int i) {
        return this.tasks.get(i);
    }

    /**
     * Deletes task of index i from TaskList.
     *
     * @param i The index of the task.
     * @return The deleted task.
     */
    public Task delete(int i) {
        this.size--;
        return this.tasks.remove(i);
    }

}
