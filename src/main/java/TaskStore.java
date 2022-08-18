import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores Tasks.
 */
public class TaskStore {
    private final List<Task> store;

    public TaskStore() {
        this.store = new ArrayList<>();
    }

    /**
     * Retrieves a specified task using an index (1-indexed).
     * @param index
     * @return The `index`-th task
     */
    public Task getTask(int index) {
        return this.store.get(index);
    }

    public String addTask(Task task) {
        this.store.add(task);
        return String.format("Got it. I've added this task:\n\t%s\nNow you have %d tasks in the list.",
                task.getDescription(), this.store.size());
    }

    /**
     * List detailed information about all tasks in this store.
     * @return String Details
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.store.size(); i++) {
            Task task = this.getTask(i);
            sb.append(String.format("%d. %s", i + 1, task.toString()));
            if (i != this.size() - 1) sb.append("\n");
        }
        return sb.toString();
    }

    public int size() {
        return this.store.size();
    }
}
