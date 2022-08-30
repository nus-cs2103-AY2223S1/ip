package jenny.tasks;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import jenny.exceptions.JennyException;
import jenny.storage.Storage;

/**
 * Handles basic CRUD operations for a list of {@link Task}.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public class TaskList {
    private static final String MESSAGE_SCOPE = TaskList.class.getSimpleName();
    private final ArrayList<Task> tasks;

    /**
     * Constructor for an instance of a new task list.
     * Will initialise a new {@link ArrayList} to store instances of {@link Task}
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for an instance of a new task list.
     * Will use the provided {@link ArrayList} to store instances of {@link Task}
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a task to the list.
     *
     * @param task a task to add to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Remove and return a task from the list using the provided index.
     *
     * @param index the index of the task in the list.
     * @return a task at the index of the list.
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application
     */
    public Task remove(int index) throws JennyException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws JennyException when runtime exceptions are thrown in the JennyBot application
     */
    public Task get(int index) throws JennyException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new JennyException(MESSAGE_SCOPE, e.getMessage());
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Saves the task list to storage.
     *
     * @param storage a task list storage.
     */
    public void save(Storage<ArrayList<Task>> storage) throws JennyException {
        storage.save(tasks);
    }

    /**
     * Returns the filtered list of {@link Task tasks} who's description passes the predicate.
     *
     * @param condition The predicate to test the task description on
     * @return filtered list of tasks
     */
    public ArrayList<Task> filter(Predicate<? super String> condition) {
        return (ArrayList<Task>) tasks.stream()
                .filter(task -> condition.test(task.getDescription()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        return tasks.toString();
    }
}
