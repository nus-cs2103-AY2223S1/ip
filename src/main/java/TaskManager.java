import java.util.ArrayList;

/**
 * Utility class to manage the list of tasks and provides augmenting operations to
 * add to the list of tasks or to enumerate through the list of tasks
 */
public class TaskManager {
    // The list of tasks
    private final ArrayList<String> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the received task into the task list
     *
     * @param task Task received from the caller
     */
    public void add(String task) {
        this.taskList.add(task);
    }

    /**
     * Return the formatted display of tasks
     *
     * @return List of formatted tasks
     */
    public String[] displayAll() {
        String[] commands = new String[this.taskList.size()];
        for (int i = 0; i < this.taskList.size(); i++) {
            commands[i] = String.format("%d. %s", i + 1, this.taskList.get(i));
        }
        return commands;
    }
}
