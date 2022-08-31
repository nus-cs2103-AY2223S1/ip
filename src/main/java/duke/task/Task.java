package duke.task;

public abstract class Task {
    private final String description;
    private final char symbol;
    private boolean isDone;

    /**
     * Initialises a {@code Task} with a symbol and description.
     * 
     * @param symbol The char representing the {@code Task}'s type.
     * @param description A string representing the {@code Task}'s description.
     * @param isDone The boolean representing whether the {@code Task} is done.
     */
    protected Task(char symbol, String description, boolean isDone) {
        this.symbol = symbol;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the char representation of whether a {@code Task} is done.
     *
     * @return The char representing the {@code Task}'s status.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the {@code Task}'s isDone to isDone.
     *
     * @param isDone The boolean to set the {@code Task}'s isDone to.
     */
    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a {@code Task}.
     *
     * @return The string representing the {@code Task}.
     */
    @Override
    public String toString() {
        return String.format("[%c][%s] %s", symbol, getStatusIcon(), description);
    }

    /**
     * Returns the string representation of a {@code Task} when stored in a data file.
     *
     * @return The string representing the {@code Task} when stored in a data file.
     */
    public String toData() {
        return String.format("%s | %s | %s", symbol, getStatusIcon(), description);
    }
}
