package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    protected void add(Task task) {
        tasks.add(task);
    }

    protected boolean isEmpty() {
        return tasks.isEmpty();
    }

    protected Task get(int index) {
        return tasks.get(index);
    }

    protected Task remove(int index) {
        return tasks.remove(index);
    }

    protected int size() {
        return tasks.size();
    }
}
