package duke.task;

/**
 * Encapsulates a task.
 */
public abstract class Task {
    private final TaskSymbolType symbol;
    private final String description;
    private boolean isDone;

    /**
     * Constructs a new {@code Task} with the given {@code symbol}, {@code description}.
     * Initially the new {@code Task} is assumed not done.
     *
     * @param symbol The {@code TaskSymbolType} of the new {@code Task}.
     * @param description The description of the new {@code Task}.
     */
    protected Task(TaskSymbolType symbol, String description) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of this {@code Task} for display.
     *
     * @return The string representation of this {@code Task} for display.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.symbol.getSymbol(), this.isDone ? "✔" : " ", this.description);
    }

    /**
     * Returns the string representation of this {@code Task} for saving in storage.
     *
     * @return The string representation of this {@code Task} for saving in storage.
     */
    public String toSaveString() {
        return String.format("%s | %d | %s", this.symbol.name(), this.isDone ? 1 : 0, this.description);
    }

    /**
     * Changes the status of this {@code Task} to provided {@code isDone}.
     *
     * @param isDone Whether this {@code Task} should be marked done or not.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if an {@code Object} is same as this {@code Task}.
     *
     * @param o The {@code Object} to check.
     * @return {@code true} if the {@code Object} is same as this {@code Task}, {@code false} otherwise.
     */
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
