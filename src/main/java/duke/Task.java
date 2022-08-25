package duke;

import java.util.ArrayList;

abstract public class Task {
    protected String description;
    protected boolean isDone;
    private static final ArrayList<Task> tasks = new ArrayList<Task>();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }



    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


    abstract public String encode();
}
