package duke;

public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getTaskName() {
        return this.name;
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }

    public void markTaskAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon()  + "] " + this.getTaskName();
    }
}
