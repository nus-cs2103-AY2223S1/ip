package duke.tasks;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);

    }

    public int size() {
        return this.tasks.size();
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public ArrayList<Task> find(String text) {
        ArrayList<Task> filtered = new ArrayList<>(this.tasks);
        filtered.removeIf(task -> !task.getDescription().contains(text));
        return filtered;
    }

}
