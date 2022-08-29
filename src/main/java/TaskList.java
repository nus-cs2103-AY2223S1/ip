import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskArray;

    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    public void add(Task task) {
        taskArray.add(task);
    }

    public void delete(int index) {
        taskArray.remove(index);
    }

    public Task get(int index) {
        return taskArray.get(index);
    }

    public int size() {
        return taskArray.size();
    }
}
