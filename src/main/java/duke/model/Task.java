package duke.model;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int numOfTasks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public static int getNumOfTasks() {
        return numOfTasks;
    }

    public static void incrementNumOfTasks() {
        numOfTasks += 1;
    }

    public static void decrementNumOfTasks() {
        numOfTasks -= 1;
    }

    public boolean contains(String description) {
        return this.description.contains(description);
    }

    public String toStorage() {
        return "";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
