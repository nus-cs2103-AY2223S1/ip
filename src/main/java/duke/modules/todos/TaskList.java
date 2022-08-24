package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static duke.Ui.sayError;

// "We will add validation to this later" - No one

/**
 * Represents a list of tasks.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> todos;

    /**
     * Constructor. Automatically loads the task list from a file.
     *
     * @param storage The Storage used to load the task list.
     */
    public TaskList(Storage storage) {
        List<Task> todos;
        try {
            todos = storage.loadList();
        } catch (MessagefulException e) {
            sayError(e);
            todos = new ArrayList<>();
        }
        this.todos = todos;
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
     * Returns an iterator for the task list.
     *
     * @return An iterator for the task list.
     */
    @Override
    public Iterator<Task> iterator() {
        return todos.iterator();
    }
}
