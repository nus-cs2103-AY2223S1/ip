package duke;

import duke.Storage;
import duke.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    protected Storage storage;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public void remove(int index) {
        tasks.remove(index);
        this.storage.update(tasks);
    }

    public void add(Task task) {
        tasks.add(task);
        this.storage.update(tasks);
    }

    public int size() {
        return this.tasks.size();
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
        this.storage.update(tasks);
    }

    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
        this.storage.update(tasks);
    }

    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            str.append("\n" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return str.toString();
    }
}
