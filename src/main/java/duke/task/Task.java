package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    private String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    private String getStatusData() {
        return (this.isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    public String toStringData() {
        return getStatusData() + " | " + this.description;
    }
}
