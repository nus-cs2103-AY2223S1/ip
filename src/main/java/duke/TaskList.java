package duke;

import java.util.ArrayList;
import java.util.List;

import duke.Task;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void markAsDone(int index) {
        this.tasks.get(index).setComplete(true);
    }

    public void markAsUndone(int index) {
        this.tasks.get(index).setComplete(false);
    }

    public TaskList filter(String searchTerm) {
        // TODO
        return new TaskList();
    }

    @Override
    public String toString() {
        // TODO
        return tasks.toString();
    }

    public int size() {
        return tasks.size();
    }
}
