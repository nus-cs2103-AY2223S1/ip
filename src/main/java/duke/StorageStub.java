package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class StorageStub implements StorageInterface {
    public List<Task> tasks;

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
}
