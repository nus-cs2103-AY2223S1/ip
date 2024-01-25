package cs2103t.ip.duke;

import java.util.ArrayList;

public class Tasklist {

    protected ArrayList<Task> tasks;

    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(Task t) {
        this.tasks.add(t);
    }

    public void deleteTasks(int i) {
        this.tasks.remove(i);
    }

    public void markDone(int taskNum) {
        this.getTasks().get(taskNum - 1).makeDone();
    }

    public void markUndone(int taskNum) {
        this.getTasks().get(taskNum - 1).makeUndone();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }
}
