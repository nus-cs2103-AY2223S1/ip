import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task, Storage storage) {
        taskList.add(task);
        storage.saveToFile(false, taskList);
    }

    public void remove(int position, Storage storage) {
        taskList.remove(position);
        storage.saveToFile(true,taskList);
    }

    public void mark(int position, Storage storage) {
        taskList.get(position).setCompleted(true);
        storage.saveToFile(true, taskList);
    }

    public void unmark(int position, Storage storage) {
        taskList.get(position).setCompleted(false);
        storage.saveToFile(true, taskList);
    }

    public Integer getSize() {
        return taskList.size();
    }

    public Task getTask(int num) {
        return taskList.get(num);
    }
}
