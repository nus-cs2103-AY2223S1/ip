package duke.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    public String getStatusIcon() {
        return ("[" + (this.isDone ? "X" : " ") + "]");
    }

    public String getDescription() {
        return this.description;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public String save() {
        return " | " + (this.isDone ? "X" : " ") + " | " + this.description;
    }
}
