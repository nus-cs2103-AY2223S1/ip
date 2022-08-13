package storage;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<String> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String task) {
        this.tasks.add(task);
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, tasks.get(i));
        }
        return tasksString;
    }

}
