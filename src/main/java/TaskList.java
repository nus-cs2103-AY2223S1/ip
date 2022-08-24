import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    // Constructor
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Instance methods
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTaskFromIndex(int taskIndex) {
        this.tasks.remove(taskIndex - 1);
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task getTaskFromIndex(int taskIndex) {
        return this.tasks.get(taskIndex - 1);
    }
}
