package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class StorageStub implements StorageInterface {
    private List<Task> tasks;

    public StorageStub(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public List<Task> readFile() throws DukeException {
        return this.tasks;
    }

    public void save(Task task) throws DukeException {
        this.tasks.add(task);
    }

    public void updateLine(int lineIndex, String updatedLine) throws DukeException {

    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
