import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> tasks;

    public ArrayList<? super String> addTasks(String task) {
        tasks.add(task);
        return tasks;
    }

    public void list() {
        if (tasks.size() <= 0) {
            System.out.println("There are no tasks in the list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + tasks.get(i));
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
