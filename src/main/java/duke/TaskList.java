package duke;

import task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Used to keep track of the current task of the chatbot.
 */
public class TaskList {
    private final List<Task> list;

    /**
     * Constructs a task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a task list with some tasks.
     *
     * @param tasks The specified tasks.
     */
    TaskList(List<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Retrieves the size of the current task list.
     *
     * @return The size of the current task list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Retrieves the task at a given index.
     *
     * @param index The specified index.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range, i.e. {@code index < 0 || index >= size()}
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return this.list.get(index);
    }

    /**
     * Removes the task at a given index.
     *
     * @param index The specified index.
     * @return The removed task.
     * @throws IndexOutOfBoundsException if the index is out of range, i.e. {@code index < 0 || index >= size()}
     */
    public Task removeTask(int index) throws IndexOutOfBoundsException {
        return this.list.remove(index);
    }

    public TaskList filter(String query) {
        return new TaskList(this.list.stream()
                .filter(task -> task.getDescription().contains(query))
                .collect(Collectors.toList())
        );
    }

    /**
     * Adds task to the current task list.
     *
     * @param task The specified task.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }
}
