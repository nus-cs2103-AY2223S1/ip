import ted.Storage;
import ted.exception.InvalidEncodingException;
import ted.task.Task;
import ted.task.TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class StorageStub extends Storage {
    private TaskList tasks = new TaskList(new ArrayList<>());

    public StorageStub(String filePath) {
        super(filePath);
    }

    @Override
    public TaskList loadTasks() throws InvalidEncodingException {
        return tasks;
    }

    @Override
    public void saveTasks(TaskList tasks) throws IOException, SecurityException {
        this.tasks = tasks;
    }
}
