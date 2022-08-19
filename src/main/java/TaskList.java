import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>(100);

    public void addTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        tasks.add(newTask);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i+1) + ". " + tasks.get(i);
            if (i != tasks.size() - 1) {
                result += "\n";
            }
        }
        return result;
    }
}
