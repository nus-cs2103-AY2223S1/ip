package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getTaskType() {
        return null;
    }

    @Override
    public String toString() {
        String taskString = "[" + this.getStatusIcon() + "] " + this.description;
        return taskString;
    }
}
