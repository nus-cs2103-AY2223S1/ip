public class Task {
    private final String description;
    private boolean isDone;
    private static int num = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        num++;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static int lsSize() {
        return num;
    }

    public String myTask() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
