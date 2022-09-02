import task_classes.Task;

import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();

    public void add(Task t) {
        list.add(t);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void remove(int i) {
        list.remove(i);
    }

    public int size() {
        return list.size();
    }

    public void loadTaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Iterator<Task> getIterator() {
        return this.list.iterator();
    }

}
