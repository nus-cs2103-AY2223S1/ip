import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("added: " + task);
    }

    public void list() {
        Task[] x = new Task[tasks.size()];
        Task[] tasksArray = tasks.toArray(x);
        for(int i = 1; i <= tasksArray.length; i++) {
            System.out.println(i + ". " + tasksArray[i - 1].toString());
        }
    }
}
