package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public abstract String getTaskType();

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setAsDone() {
        this.isDone = true;
    }

    public void setAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
