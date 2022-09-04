package pikachu.task;
abstract public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean newDone) {
        this.isDone = newDone;
    }

    abstract public String getName();
    abstract public String getTiming();

    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + getDescription();
    }
}
