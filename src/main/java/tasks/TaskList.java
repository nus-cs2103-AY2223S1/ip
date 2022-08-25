package tasks;

import java.util.ArrayList;
import java.util.List;

enum SortType {
    name,
    time,
    status,
}

/**
 * This class encapsulates a list of tasks.
 */
public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for
     *
     * @param taskList The task list to be used.
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the task at the specified index
     *
     * @param index The index to retrieve from.
     * @return The task.
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the size of the task list
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Removes the task at the specified index
     *
     * @param index The task to be removed.
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    /**
     * Returns the task list
     *
     * @return The task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }
}
