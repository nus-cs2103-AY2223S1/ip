import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }
    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public int size() {
        return this.tasks.size();
    }

}
