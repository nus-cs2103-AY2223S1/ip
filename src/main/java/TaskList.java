import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    protected void add(Task task) {
        taskList.add(task);
    }

    protected Task get(int index) {
        return taskList.get(index);
    }

    protected void remove(int index) {
        taskList.remove(index);
    }

    protected boolean isEmpty() {
        return taskList.isEmpty();
    }

    protected int size() {
        return taskList.size();
    }
}
