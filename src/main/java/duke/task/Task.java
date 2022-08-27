package duke.task;

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

    public String getDescription() {
        return this.description;
    }

    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    public String toString() {
        return "["+ this.getStatusIcon() +"] " + this.description;
    }

}