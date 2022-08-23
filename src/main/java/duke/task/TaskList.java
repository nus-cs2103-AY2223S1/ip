package duke.task;

import java.util.ArrayList;
import java.util.Objects;

import duke.DukeException;
import duke.Storage;

/**
 * Representation of a list of tasks.
 */
public class TaskList extends ArrayList<Task> {
    private final Storage storage;

    /**
     * Constructs a new task list with storage object to save updates to file.
     * @param storage storage object representing a text file to save tasks to.
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
     * Add new task to list
     * @param task new task
     * @return true if task was added, false otherwise
     *
     * @throws DukeException if task could not be saved.
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
     * Remove task from list.
     * @param index index of task to remove
     * @return Task that was removed
     * @throws DukeException if task could not be found and removed.
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
