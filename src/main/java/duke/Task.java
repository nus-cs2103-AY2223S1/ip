package duke;

public class Task {
    protected String taskName;
    protected boolean isDone;
    protected int priority;

    public Task(String taskName, boolean isDone, int priority) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setPriority(String pr) {
        if (pr.equals("high")) {
            this.priority = 1;
        }
        if (pr.equals("low")) {
            this.priority = 0;
        }
    }

    public int getPriority() {
        return this.priority;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    public String toStore() {
        return (this.isDone ? 1 : 0) + "|" + this.taskName;
    }
}
