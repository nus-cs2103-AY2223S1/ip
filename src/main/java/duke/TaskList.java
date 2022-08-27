package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> allTasks;

    public TaskList(ArrayList<Task> allTasks) {
        this.allTasks = allTasks;
    }

    public int size() {
        return this.allTasks.size();
    }

    public Task get(int index) {
        return this.allTasks.get(index);
    }

    public void add(Task t) {
        this.allTasks.add(t);
    }

    public Task remove(int index) {
        return this.allTasks.remove(index);
    }

    public TaskList() {
        this.allTasks = new ArrayList<Task>();
    }
}
