package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> list = new ArrayList<>();

    public TaskList() {}

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void delete(int i) {
        this.list.remove(i - 1);
    }

    public int size() {
        return this.list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public Task getTask(int i) {
        return this.list.get(i - 1);
    }
}
