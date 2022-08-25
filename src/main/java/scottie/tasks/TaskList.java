package scottie.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Encapsulates a list of Tasks.
 * The tasks are initially loaded from a Storage and any changes
 * to the list are always immediately saved to the Storage.
 */
public class TaskList implements Iterable<Task> {
    private final Storage storage;
    private final List<Task> tasks;

    /**
     * Constructs a TaskList.
     */
    public TaskList() {
        this.storage = new Storage();
        this.tasks = new ArrayList<>();
        for (String taskData : this.storage.loadTasksData()) {
            try {
                this.addTask(Task.fromEncodedString(taskData));
            } catch (InvalidTaskDataException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns whether this TaskList has no Tasks.
     *
     * @return True if this TaskList has no Tasks, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    /**
     * Returns the number of Tasks in this TaskList.
     *
     * @return The number of Tasks in thie TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the Task at the given index.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to return.
     * @return The Task at the specified index.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds the given Task to the TaskList.
     * It is added as the last Task in the list.
     *
     * @param task The Task to add the TaskList.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.saveTasks();

    }

    /**
     * Deletes the Task at the given index from the TaskList.
     * The list is 0-indexed. The indices of the tasks after the given
     * index will be shifted to fill the removed index.
     *
     * @param index The index of the Task to remove from the list.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
        this.saveTasks();
    }

    /**
     * Marks the task at the given index as done.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to mark as done.
     */
    public void markTask(int index) {
        this.getTask(index).markAsDone();
        this.saveTasks();
    }

    /**
     * Marks the task at the given index as not done.
     * The list is 0-indexed.
     *
     * @param index The index of the Task to mark as not done.
     */
    public void unmarkTask(int index) {
        this.getTask(index).markAsUndone();
        this.saveTasks();
    }

    /**
     * Returns a List of Tasks in this TaskList whose
     * descriptions contain the given searchText.
     * This search is case-insensitive.
     *
     * @param searchText The text to search for.
     * @return A List of matching Tasks.
     */
    public List<Task> filterTasks(String searchText) {
        return this.tasks.stream()
                .filter(task -> task.matchesAgainst(searchText))
                .collect(Collectors.toList());
    }

    /**
     * Saves the tasks in this list to the storage.
     * This method is called whenever the tasks in the list
     * are modified.
     */
    private void saveTasks() {
        this.storage.saveTasks(this.tasks);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
