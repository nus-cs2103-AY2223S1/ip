public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String getAddMessage(int numberTasks) {
        return "Got it. I've added this task:\n" + this + "\nNow you have " + numberTasks + " tasks in the list";
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
