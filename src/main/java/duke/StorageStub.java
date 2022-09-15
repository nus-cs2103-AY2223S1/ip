package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Stub implementation for StorageInterface for testing.
 */
public class StorageStub implements StorageInterface {
    private final List<Task> tasks;

    /**
     * Constructor for Storage stub
     *
     * @param tasks Initial list of tasks.
     */
    public StorageStub(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    @Override
    public List<Task> readFile() {
        return this.tasks;
    }

    @Override
    public void save(Task task) {
        this.tasks.add(task);
    }

    @Override
    public void deleteLine(int lineIndex) {
    }

    @Override
    public void updateLine(int lineIndex, String updatedLine) {
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
