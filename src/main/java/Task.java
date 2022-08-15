public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
        System.out.println("Task marked as done:\n\t" + this);
    }

    public void markAsNotDone() {
        isDone = false;
        System.out.println("Task marked as not done:\n\t" + this);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
