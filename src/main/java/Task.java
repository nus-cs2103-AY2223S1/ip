public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String toWrite() {
        return "";
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskTypeIcon() { return "T"; }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}