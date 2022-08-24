public class Task {
    protected String description;
    protected boolean isDone;
    public static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String added() {
        return String.format("Got it. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list.\n", this, Task.taskCount);
    }

    public String toString() {
        return "This is a task";

    }

}
