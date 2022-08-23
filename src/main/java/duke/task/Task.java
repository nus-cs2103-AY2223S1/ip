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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone &&
                symbol == task.symbol &&
                description.equals(task.description);
    }
}
