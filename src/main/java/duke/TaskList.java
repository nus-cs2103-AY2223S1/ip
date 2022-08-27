package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        Task t = tasks.get(index);
        return t;
    }

    public Task remove(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }
}
