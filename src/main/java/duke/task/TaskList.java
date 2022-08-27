package duke.task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        return task;
    }

    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    public Task changeTaskStatus(int taskId, boolean isDone) {
        Task task = tasks.get(taskId - 1);
        task.setIsDone(isDone);
        return task;
    }
}
