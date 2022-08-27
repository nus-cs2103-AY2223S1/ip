package duke.tasks;

import java.util.ArrayList;

import duke.storage.Storage;

public class TaskList {

    private final Storage storage;
    private final ArrayList<Task> tasks;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = storage.load();
    }

    public Task getTask(int idx) {
        return tasks.get(idx);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int idx) {
        return tasks.remove(idx - 1);
    }

    public void updateStorage() {
        storage.update(tasks);
    }

    public int getSize() {
        return tasks.size();
    }
}
