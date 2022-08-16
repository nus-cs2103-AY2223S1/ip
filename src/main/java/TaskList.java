import java.util.ArrayList;

public class TaskList {
    private final ArrayList<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(String task) {
        taskList.add(task);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) return "List is empty";
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(". ").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }
}
