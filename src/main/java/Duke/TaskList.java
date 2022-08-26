package Duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> listTasks() {
        return list;
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void remove(int i) {
        list.remove(i);
    }

}
