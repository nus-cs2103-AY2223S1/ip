package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a task list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates a new instance of an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a new instance of a task list with some tasks.
     *
     * @param taskList The initial tasks in the task list.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves the task at a given index in the task list.
     *
     * @param index The index of a task in the task list.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to add to the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task The task to be removed from the task list.
     */
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * Finds the matching tasks in the task list by the given query.
     *
     * @param query The given search query.
     * @return The list of matching tasks
     */
    public List<Task> findTask(String query) {
        return this.taskList.stream()
                .filter(task -> task.containsQuery(query))
                .collect(Collectors.toList());
    }
}
