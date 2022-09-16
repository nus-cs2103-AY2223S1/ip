package duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public char getStatusIcon() {
        return isDone ? 'X' : ' '; // done = X
    }

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type, getStatusIcon(), description);
    }

    public String data() {
        return String.format("%c, %c, %s", type,  getStatusIcon(), description);
    }
}
