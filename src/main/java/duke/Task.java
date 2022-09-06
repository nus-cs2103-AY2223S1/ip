package duke;

public class Task {
    protected String name;
    protected boolean isDone;

    // constructor
    public Task(String taskName) {
        name = taskName;
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }


    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }




}
