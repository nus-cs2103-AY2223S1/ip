package ted.task;

import java.util.ArrayList;

/**
 * A class that encapsulate a collection of tasks.
 */
public class TaskList {

    /**
     * Internal stores of tasks
     */
    private ArrayList<Task> tasks;

    /**
     * Construct a Task list instance
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task into tasks list
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Get a task from tasks list
     * @param index
     * @return the task
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Return the last task in tasks list
     * @return the last task
     */
    public Task last() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    /**
     * Delete a task by index
     * @param index of the task to be deleted
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Size of the tasks list
     * @return size
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Construct an empty task list
     * @return an empty task list
     */
    public static TaskList empty() {
        return new TaskList(new ArrayList<>());
    }

    @Override
    public String toString() {
        String str = "";
        for (int inputIndex = 0; inputIndex < tasks.size(); inputIndex++) {
            str += String.format("%d.%s\n", inputIndex + 1, tasks.get(inputIndex));
        }
        return str;
    }
}
