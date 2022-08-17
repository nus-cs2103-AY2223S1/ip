import java.util.ArrayList;

/**
 * Utility class to manage the list of tasks and provides augmenting operations to
 * add to the list of tasks or to enumerate through the list of tasks
 *
 * @author Emily Ong Hui Qi
 */
public class TaskManager {
    // The list of tasks
    private final ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the received task into the task list
     *
     * @param taskDescription Task description received from the caller
     */
    public void add(String taskDescription) {
        this.taskList.add(new Task(taskDescription));
    }

    /**
     * Retrieve the task
     *
     * @param taskIndex An index (1-index) corresponding to a particular task
     * @return Task corresponding to the particular task index
     */
    public Task get(int taskIndex) {
        // FIXME: Error handling if the task index is invalid
        return this.taskList.get(taskIndex - 1);
    }

    @Override
    public String toString() {
        StringBuilder taskManagerDisplay = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            // Implicitly invoke the display of the task defined in the Task class
            taskManagerDisplay.append(String.format("%d. %s\n", i + 1, this.taskList.get(i)));
        }
        return taskManagerDisplay.toString();
    }
}
