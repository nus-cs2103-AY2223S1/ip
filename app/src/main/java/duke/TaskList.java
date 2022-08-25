package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task deleteTask(int id) {
        return tasks.remove(id);
    }

}
