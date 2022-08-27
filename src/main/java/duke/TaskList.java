package duke;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        Task t = taskList.get(index);
        return t;
    }

    public Task remove(int index) {
        Task t = taskList.remove(index);
        return t;
    }

    public int size() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
