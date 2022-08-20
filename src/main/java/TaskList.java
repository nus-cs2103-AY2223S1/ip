import java.io.IOException;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

    private final Storage storage;

    public TaskList(Storage storage) {
        super();
        this.storage = storage;

    }

    private void syncStorageState() {
        try {
            this.storage.writeToFile(StorageConverter.tasksToString(this));
        } catch (IOException e) {
            System.out.println("Sync storage state failed. " + e.getMessage());
        }
    }

    @Override
    public boolean add(Task task) {
        super.add(task);
        syncStorageState();
        return true;
    }

    @Override
    public Task remove(int index) {
        Task t = super.remove(index);
        syncStorageState();
        return t;
    }

    public void mutatedTask() {
        syncStorageState();
    }

}
