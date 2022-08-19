import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>(100);

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String markAsDone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsDone();
        return task.toString();
    }

    public String markAsUndone(int taskId) {
        Task task = tasks.get(taskId);
        task.markAsUndone();
        return task.toString();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i);
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
