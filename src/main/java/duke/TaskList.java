package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the application.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
}
