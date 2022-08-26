package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Stub implementation for StorageInterface for testing.
 */
public class StorageStub implements StorageInterface {
    private List<Task> tasks;

    /**
     * Constructor for Storge stub
     *
     * @param tasks initial list of tasks.
     */
    public StorageStub(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public List<Task> readFile() throws DukeException {
        return this.tasks;
    }

    @Override
    public void save(Task task) throws DukeException {
        this.tasks.add(task);
    }

    @Override
    public void deleteLine(int lineIndex) throws DukeException {
    }

    @Override
    public void updateLine(int lineIndex, String updatedLine) throws DukeException {
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
