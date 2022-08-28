package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> addTask(Task t) {
        tasks.add(t);
        return tasks;
    }

    public Task deleteTask(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    public ArrayList<Task> markTask(Task t, boolean b) {
        t.setMarked(b);
        return tasks;
    }
}
