import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    TaskList(ArrayList<Task> tasks) {
        this.taskList = new ArrayList<>(tasks);
    }

    int numOfTasks() {
        return taskList.size();
    }

    Task fetchTask(int taskIndex) {
        return taskList.get(taskIndex - 1);
    }
}
