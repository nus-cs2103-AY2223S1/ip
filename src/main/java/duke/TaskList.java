package duke;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.Task;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified index.
     * @param index the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     * @param index the index of the task to remove
     * @return the task that was removed
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Marks the task at the specified index as complete.
     * @param index the index of the task to mark as complete
     */
    public void markAsDone(int index) {
        this.tasks.get(index).setComplete(true);
    }

    /**
     * Marks the task at the specified index as incomplete.
     * @param index the index of the task to mark as incomplete
     */
    public void markAsUndone(int index) {
        this.tasks.get(index).setComplete(false);
    }

    @Override
    public String toString() {
        // TODO
        return tasks.toString();
    }

    public String toDisplayString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            stringBuilder.append((String.format("\n%3d: %s", i + 1, tasks.get(i).toString())));
        }

        return stringBuilder.toString();
    }

    /**
     * Returns the number of tasks in the list.
     * @return the number of tasks in the list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a stream of Tasks.
     * @return a stream of Tasks.
     */
    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    /**
     * Sorts the tasks by their name.
     */
    public void sortTasksByName() {
        Comparator<Task> nameComparator = Comparator.comparing(Task::getName);
        tasks.sort(nameComparator);
    }
}
