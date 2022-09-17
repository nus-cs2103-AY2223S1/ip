package jarvis.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Returns a TaskList object with the given tasks
     *
     * @param tasks Initial List of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns an empty TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * @return List of tasks
     */
    public List<Task> getList() {
        return tasks;
    }

}
