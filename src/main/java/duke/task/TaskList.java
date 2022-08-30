package duke.task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.IntStream;

import duke.DukeException;
import duke.Storage;

/**
 * List of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private final Storage storage;

    public TaskList() {
        this(null);
    }

    /**
     * Creates a tasklist which will save tasks to a given storage automatically.
     *
     * @param storage storage object to save and load tasks.
     */
    public TaskList(Storage storage) {
        super(100);
        if (storage != null) {
            try {
                this.addAll(storage.loadTasks());
            } catch (DukeException e) {
                // initialize with no tasks.
            }
        }
        this.storage = storage;
    }

    /**
     * Finds all tasks in list that matches the keyword.
     *
     * @param keyword Word to be searched for.
     * @return filtered list of task.
     */
    public ArrayList<Task> filterByKeyword(String keyword) {
        TaskList filtered = new TaskList();
        for (Task task : this) {
            if (task.matches(keyword)) {
                filtered.add(task);
            }
        }
        return filtered;
    }


    /**
     * Adds task to list.
     *
     * @param task Task to be added
     * @return Whether the task was added successfully.
     * @throws DukeException any exception when trying to add new task to the list.
     */
    public boolean addTask(Task task) throws DukeException {
        if (super.add(task)) {
            if (storage != null) {
                storage.saveTasks(this);
            }
            return true;
        }
        return false;
    }

    /**
     * Removes the specified task from the list.
     *
     * @param index Index of the task to be removed.
     * @return Task that was removed.
     * @throws DukeException any error when removing the task from the list.
     */
    public Task removeTask(int index) throws DukeException {
        if (0 <= index && index <= this.size()) {
            Task task = super.remove(index);
            storage.saveTasks(this);
            return task;
        } else {
            throw new DukeException("Failed to delete task %d", index + 1);
        }
    }

    /**
     * Sets the completion status of a task in the list.
     *
     * @param index The index of task to change.
     * @param completed new completion status of task.
     * @return The task that was changed.
     * @throws DukeException any error when changing the completion status of the task.
     */
    public Task setCompletion(int index, boolean completed) throws DukeException {
        try {
            Task task = super.get(index);
            task.setDone(completed);
            if (storage != null) {
                storage.saveTasks(this);
            }
            return task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Task %d not found.", index + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        IntStream.range(0, this.size()).forEach(i -> output.append(String.format("\t%d. %s%n", i + 1, this.get(i))));
        return output.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskList)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        TaskList tasks = (TaskList) o;
        return Objects.equals(storage, tasks.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), storage);
    }
}
