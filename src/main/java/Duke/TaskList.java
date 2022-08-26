package Duke;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public ArrayList<Task> listTasks() {
        return list;
    }
}
