import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
    }

    Task markTask(int index) {
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    Task unmarkTask(int index) {
        tasks.get(index).unmarkAsDone();
        return tasks.get(index);
    }

    List<String> getAllTasks() {
        List<String> numberedTaskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            numberedTaskList.add(String.format("%d.%s", i + 1, tasks.get(i).toString()));
        }
        return numberedTaskList;
    }

    @Override
    public String toString() {
        return String.join("/n", getAllTasks());
    }
}
