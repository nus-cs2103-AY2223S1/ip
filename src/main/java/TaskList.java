import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
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
