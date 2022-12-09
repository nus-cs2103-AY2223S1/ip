package duke;

/**
 * Encapsulates a task to be completed.
 *
 * @author Conrad
 */

public class Task {
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Constructor for creating a task.
     *
     * @param taskDescription A description of the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isCompleted = false;
    }


    /**
     * Marks the task as complete.
     *
     */
    public void setCompleted() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     *
     */
    public void setUncompleted() {
        this.isCompleted = false;
    }

    /**
     * Checks if the given task is completed.
     *
     * @return A boolean representing whether the task is marked as completed.
     */
    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getTextRepresentation() {
        return "Invalid Type";
    }

    /**
     * Retrieves the description of the task.
     *
     * @return A string representing the description of the task.
     */
    public String getTaskDescription() {
        return this.taskDescription;
    }


    /**
     * Returns a string representation of a task.
     *
     * @return The string representation of a task.
     */
    @Override
    public String toString() {
        String taskStatusIndicator = this.isCompleted ? " [X] " : " [ ] ";
        return taskStatusIndicator + this.taskDescription;
    }

    public boolean matchesString(String searchInput) {
        return this.taskDescription.contains(searchInput);
    }
}
