import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;

    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


}
