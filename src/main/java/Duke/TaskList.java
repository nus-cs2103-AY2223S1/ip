package Duke;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public static List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void add(Task task) {
        taskList.add(task);
    }
}
