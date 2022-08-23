package ted.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void pop() {
        this.tasks.remove(this.tasks.size() - 1);
    }

    public Task last() {
        return this.tasks.get(this.tasks.size() - 1);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public static TaskList empty() {
        return new TaskList(new ArrayList<>());
    }

    @Override
    public String toString() {
        String str = "";
        for (int inputIndex = 0; inputIndex < tasks.size(); inputIndex++) {
            str += String.format("%d.%s\n", inputIndex + 1, tasks.get(inputIndex));
        }
        return str;
    }
}
