package duke.data;

import duke.data.tasks.Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int taskIdx) {
        return this.tasks.get(taskIdx);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskIdx) {
        Task deletedTask = this.getTask(taskIdx);
        this.tasks.remove(taskIdx);
        return deletedTask;
    }
}
