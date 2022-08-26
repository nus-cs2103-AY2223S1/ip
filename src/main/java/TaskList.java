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

    boolean isEmpty() {
        return numOfTasks() == 0;
    }

    Task removeTask(int index) {
        return taskList.remove(index);
    }

    void addTask(Task task) {
        taskList.add(task);
    }
}
