import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<String> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    void addTask(String task) {
        tasks.add(task);
    }

    List<String> getAllTasks() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return numberedTaskList;
    }
}
