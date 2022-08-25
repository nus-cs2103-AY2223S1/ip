package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }



    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
