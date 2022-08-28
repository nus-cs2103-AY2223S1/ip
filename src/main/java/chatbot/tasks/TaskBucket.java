package chatbot.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents a group of task which shares a certain attribute.
 * For the current use case, all tasks in a specific instance will have
 * the same date.
 */
public class TaskBucket {
    private List<Task> tasksToday = new ArrayList<>();

    public void addTask(Task task) {
        this.tasksToday.add(task);
    }

    public List<Task> getTasks() {
        return this.tasksToday;
    }
}
