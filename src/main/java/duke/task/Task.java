package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String saveText() {
        return String.format("Task|%d|%s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return "[" + statusIcon + "] " + this.description;
    }
}