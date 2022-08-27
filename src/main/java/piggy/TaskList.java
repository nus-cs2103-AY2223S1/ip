package piggy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import piggy.task.Task;

/**
 * Handles a list of tasks.
 */
class TaskList implements Iterable<Task> {
    private List<Task> tasks;

    /**
     * Creates a new TaskList from a List of Tasks.
     *
     * @param tasks The list of tasks.
     */
    TaskList(List<Task> tasks) {
        if (tasks != null) {
            this.tasks = tasks;
        } else {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Adds a new task.
     *
     * @param task The task to add.
     */
    void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task.
     *
     * @param index The 0-based index of the task to remove.
     * @return The removed task.
     */
    Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index
     *
     * @param index The index to get the task from.
     * @return The task at the index.
     */
    Task get(int index) {
        return tasks.get(index);
    }

    int size() {
        return tasks.size();
    }

    /**
     * Returns an Iterator over the tasks.
     *
     * @return An iterator over the tasks.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Returns a List of the Tasks.
     *
     * @return A list of tasks.
     */
    public List<Task> toList() {
        return new ArrayList<>(tasks);
    }

    /**
     * Returns a list of tasks whose description contains the keyword.
     *
     * @param keyword The keyword to find.
     * @return The list of tasks that match.
     */
    public List<Task> find(String keyword) {
        return tasks.stream().filter(task -> task.getDescription().contains(keyword)).collect(Collectors.toList());
    }
}
