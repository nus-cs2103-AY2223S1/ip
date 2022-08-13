package storage;
import java.util.ArrayList;
import java.util.List;
import task.Task;

public class Storage {
    private List<Task> tasks;

    public Storage() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task getTaskWithIndex(int index) {
        return this.tasks.get(index);
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i).toString());
        }
        return tasksString;
    }

}
