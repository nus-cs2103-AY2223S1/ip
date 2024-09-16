package tasks;

import java.time.LocalDateTime;

/**
 * Specify the task to be done.
 */
public abstract class Task {
    private String taskDescription;
    private boolean isCompleted;

    /**
     * Constructor for the Task class.
     * @param taskDescription Description of the task.
     */
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isCompleted = false;
    }

    /**
     * Mark this specific task as done.
     */
    public void markDone() {
        isCompleted = true;
    }

    /**
     * Unmark this specific task to be undone.
     */
    public void unmarkDone() {
        isCompleted = false;
    }

    /**
     * Check whether the task is marked, and update the status icon
     * to be an "X".
     * @return Status icon on whether the task is marked or unmarked.
     */
    protected String getStatusIcon() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Get description for this task.
     * @return Task description for the task.
     */
    protected String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Check whether the task has the keyword in the task description.
     * @param keyword Keyword to be found in task description.
     * @return True if keyword is found, False otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return taskDescription.contains(keyword);
    }

    /**
     * Abstract class to parse the task class into a String
     * to be saved into the local txt file.
     * @return A string format to save the tasks into the local txt file.
     */
    public abstract String parseToFile();

    /**
     * Get the date and time of the task.
     * @return the date and time of the task within a LocalDateTime object.
     */
    public abstract LocalDateTime getTaskDateTime();

    /**
     * Return a formatted task to the user.
     * @return Formatted task to the user.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getTaskDescription());
    }
}
