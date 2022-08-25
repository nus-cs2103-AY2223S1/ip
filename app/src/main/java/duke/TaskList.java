package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a specific task from the list.
     * @param id The id of the task.
     * @return The task.
     */
    public Task getTask(int id) {
        return tasks.get(id);
    }

    /**
     * Removes a specific task from the list.
     * @param id The id of the task.
     * @return The removed task.
     */
    public Task deleteTask(int id) {
        return tasks.remove(id);
    }

    /**
     * Creates a new task list handler.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }
}
