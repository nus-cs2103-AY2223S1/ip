package duke;

/**
 * Task class to represent a generic task.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class Task {
    protected String description;
    protected boolean isDone;
    private static int taskCount = 0;

    /**
     * Constructor for Task class
     *
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Increments the taskCount
     */

    public static void addTaskCount() {
        taskCount++;
    }

    /**
     * Decrements the taskCount
     *
     */

    public static void minusTaskCount() {
        taskCount--;
    }

    /**
     * Retrieves the taskCount
     *
     * @return int taskCount
     */

    public static int getTaskCount() { return Task.taskCount; }

    /**
     * Retrieves the status icon of the task: done or undone.
     *
     * @return String "X" or " "
     */

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */

    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone
     */

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Updates the task description
     *
     * @param updatedField new description
     */

    public void updateDescription(String updatedField) {
        this.description = updatedField;
    }

    /**
     * Returns a String for Duke output
     *
     * @return String
     */

    public String added() {
        return String.format("New task added:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.", this, Task.getTaskCount());
    }

    public String toString() {
        return "This is a task";

    }

}
