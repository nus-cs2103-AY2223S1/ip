package duke;

import java.util.List;

import duke.task.Task;

public interface StorageInterface {

    public List<Task> readFile() throws DukeException;

    public void save(Task task) throws DukeException;

    public void updateLine(int lineIndex, String updatedLine) throws DukeException;
}
