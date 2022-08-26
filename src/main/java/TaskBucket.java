import java.util.ArrayList;
import java.util.List;

public class TaskBucket {
    private List<Task> tasksToday = new ArrayList<>();

    public void addTask(Task task) {
        this.tasksToday.add(task);
    }

    public List<Task> getTasks() {
        return this.tasksToday;
    }
}
