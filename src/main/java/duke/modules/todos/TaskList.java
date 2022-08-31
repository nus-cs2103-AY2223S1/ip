package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static duke.Ui.sayAsError;

// "We will add validation to this later" - No one

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private List<Task> todos;

    /**
     * Constructor for creating an empty TaskList.
     */
    public TaskList() {
        this.todos = new ArrayList<>();
    }

    /**
     * Populates the TaskList with entries loaded from the storage.
     *
     * @param storage The Storage used to load the task list.
     * @return A list of text feedback from this command, if any.
     */
    public List<String> populate(Storage storage) {
        try {
            this.todos = storage.loadList();
        } catch (MessagefulException e) {
            this.todos = new ArrayList<>();
            return sayAsError(e.getHint());
        }
        return List.of();
    }

    /**
     * Gets the task with the given index.
     *
     * @param index The index of the task to load.
     * @return The specified task.
     */
    public Task get(int index) {
        return todos.get(index);
    }

    /**
     * Returns the size of the list.
     *
     * @return The size of the list.
     */
    public int size() {
        return todos.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return Whether the task is successfully added.
     */
    public boolean add(Task task) {
        return todos.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task remove(int index) {
        return todos.remove(index);
    }

    /**
     * Returns a sub-TaskList containing only tasks whose names contain all the given segments.
     *
     * @param segments The string segments the return tasks will contain.
     * @return The matching tasks.
     */
    public TaskList filter(List<String> segments) {
        TaskList result = new TaskList();

        for (Task task : this.todos) {
            String name = task.getName();
            if (segments.stream().allMatch(name::contains)) {
                result.add(task);
            }
        }

        return result;
    }

    /**
     * Returns an iterator for the task list.
     *
     * @return An iterator for the task list.
     */
    @Override
    public Iterator<Task> iterator() {
        return todos.iterator();
    }
}
