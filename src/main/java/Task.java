/**
 * This class encapsulate the behavior of a task
 */
public class Task {
    private final String taskDescription;
    private boolean taskStatus;

    /**
     * Constructor for Task class
     * @param taskDescription the content of the task
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.taskStatus = false;
    }

    /**
     * Change the status of a task
     */
    public void changeStatus(boolean newStatus) {
        this.taskStatus = newStatus;
    }

    @Override
    public String toString() {
        if (taskStatus) {
            return "[X] " + taskDescription;
        } else {
            return "[] " + taskDescription;
        }
    }
}
