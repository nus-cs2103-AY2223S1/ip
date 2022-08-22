package duke.task;

public abstract class Task {
    private final char symbol;
    private final String description;
    private boolean isDone;

    protected Task(char symbol, String description) {
        this.description = description;
        this.symbol = symbol;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", this.symbol, this.isDone ? "✔" : " ", this.description);
    }

    public String toSaveString() {
        return String.format("%c | %d | %s", this.symbol, this.isDone ? 1 : 0, this.description);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
