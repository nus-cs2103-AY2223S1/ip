package yilia.task;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public int size() {
        return tasks.size();
    }
    public Task get(int index) {
        return tasks.get(index - 1);
    }
    public void add(Task task) {
        tasks.add(task);
    }
    public Task remove(int index) {
        return tasks.remove(index - 1);
    }
}