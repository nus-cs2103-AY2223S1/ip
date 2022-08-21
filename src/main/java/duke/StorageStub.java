package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Stub implementation for StorageInterface for testing.
 */
public class StorageStub implements StorageInterface {
    public List<Task> tasks;

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
    public void updateLine(int lineIndex, String updatedLine) throws DukeException {
    }
}
