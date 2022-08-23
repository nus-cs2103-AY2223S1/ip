package duke.task;

public abstract class Task {
    private final TaskSymbolType symbol;
    private final String description;
    private boolean isDone;

    protected Task(TaskSymbolType symbol, String description) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.symbol.getSymbol(), this.isDone ? "✔" : " ", this.description);
    }

    public String toSaveString() {
        return String.format("%s | %d | %s", this.symbol.name(), this.isDone ? 1 : 0, this.description);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof  Task) {
            Task other = (Task) o;
            return this.symbol.equals(other.symbol) &&
                    this.description.equals(other.description) &&
                    this.isDone == other.isDone;
        }
        return false;
    }
}
