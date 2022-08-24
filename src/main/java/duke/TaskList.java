package duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void removeTask(Task remove) {
        this.taskList.remove(remove);
    }

    public void addTask(Task t) {
        this.taskList.add(t);
    }

    public int getLength() {
        return taskList.size();
    }

    public Task getTaskAt(int index) {
        return taskList.get(index);
    }
}
