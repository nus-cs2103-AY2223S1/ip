/**
 * Encapsulates a task to be completed.
 *
 * @author Conrad
 */

public class Task {
    private String taskDescription;
    private boolean completed;

    /**
     * Constructor for creating a task.
     *
     * @param taskDescription A description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.completed = false;
    }


    /**
     * Marks the task as complete.
     *
     */
    public void setCompleted() {
        this.completed = true;
    }

    /**
     * Marks the task as incomplete.
     *
     */
    public void setUncompleted() {
        this.completed = false;
    }

    /**
     * Checks if the given task is completed.
     *
     * @return A boolean representing whether the task is marked as completed.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Get the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }


    /**
     * Return a string representation of a task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        String taskStatusIndicator = this.completed ? " [X] " : " [ ] ";
        return taskStatusIndicator + this.taskDescription;
    }
}
