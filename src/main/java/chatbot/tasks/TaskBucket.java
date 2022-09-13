package chatbot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a group of task which shares a certain attribute.
 * Currently, the shared attribute can be a date or a tag.
 */
public class TaskBucket {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
