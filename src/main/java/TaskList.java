import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public int totalTask() {
        return taskList.size();
    }

    public void markTaskN(int n, boolean isDone) {
        this.taskList.get(n - 1).isDoneSetter(isDone);
    }

    public Task getTaskN(int n) {
        // start counting from 1
        return this.taskList.get(n - 1);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) return "List is empty";
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            result.append(i + 1).append(".").append(taskList.get(i)).append(i == taskList.size() - 1 ? "" : "\n");
        }
        return result.toString();
    }
}
