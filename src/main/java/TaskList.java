import java.util.ArrayList;
import java.util.List;
public class TaskList {

    protected List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public Task deleteTask(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }

    public void markTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    public void unmarkTask(int taskNumber) {
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

}
