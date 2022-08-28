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

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void addTaskCount() {
        taskCount++;
    }

    public static void minusTaskCount() {
        taskCount--;
    }

    public static int getTaskCount() { return Task.taskCount; }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String added() {
        return String.format("New task added:\n" +
                "  %s\n" +
                "Now you have %d tasks in the list.\n", this, Task.getTaskCount());
    }

    public String toString() {
        return "This is a task";

    }

}
