import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public int getSize() {
        return this.list.size();
    }

    public Task getTask(int num) {
        return this.list.get(num);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task removeTask(int num) {
        return this.list.remove(num);
    }
}
