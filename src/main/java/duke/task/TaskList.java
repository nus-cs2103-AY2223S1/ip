package duke.task;

import duke.DukeException;
import duke.Storage;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TaskList extends ArrayList<Task> {
    private final Storage storage;

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


    public boolean addTask(Task task) throws DukeException {

        if (super.add(task)) {
            if (storage != null) {
                storage.saveTasks(this);
            }
            return true;
        }
        return false;
    }

    public Task removeTask(String index) throws DukeException {
        try {
            Task task = super.remove(parseInt(index.strip()) - 1);
            storage.saveTasks(this);
            return task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Failed to delete task %s", index);
        }
    }

    public Task setCompletion(String index, boolean completed) throws DukeException {
        try {
            Task task = super.get(Integer.parseInt(index.strip()) - 1);
            task.setDone(completed);
            storage.saveTasks(this);
            return task;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Task %d not found.", index);
        }
    }

}
