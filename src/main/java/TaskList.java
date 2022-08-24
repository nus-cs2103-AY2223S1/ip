import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskL;

    public TaskList() {
        this.taskL = new ArrayList<Task>();
    }

    public int size() {
        return this.taskL.size();
    }

    public Task add(Task t) {
        this.taskL.add(t);
        return t;
    }

    public void delete(int i) {
        this.taskL.remove(i-1);
    }

    public Task get(int i) {
        return this.taskL.get(i);
    }

    public ArrayList<Task> getTasks() {
        return this.taskL;
    }

    public Task mark(int i) {
        Task task = taskL.get(i-1);
        task.setStatusIcon(true);
        return task;
    }

    public Task unmark(int i) {
        Task task = taskL.get(i-1);
        task.setStatusIcon(false);
        return task;
    }
}
