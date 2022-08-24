import java.util.ArrayList;

public class Tasklist {

    private final ArrayList<Task> taskList;

    public Tasklist() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(int index) {
        taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }

}