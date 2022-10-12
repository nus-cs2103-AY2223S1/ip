package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Task implements Serializable {
    private final char symbol;
    private final String description;
    private final LocalDateTime time;
    private boolean isDone;

    /**
     * Initialises a {@code Task} with a symbol and description.
     *
     * @param symbol The char representing the {@code Task}'s type.
     * @param description A string representing the {@code Task}'s description.
     * @param isDone The boolean representing whether the {@code Task} is done.
     */
    protected Task(char symbol, String description, LocalDateTime time, boolean isDone) {
        assert !description.isBlank();
        this.symbol = symbol;
        this.description = description;
        this.time = time;
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
     * Returns a {@code Task}'s description.
     *
     * @return The string representing the {@code Task}'s description.
     */
    protected String getDescription() {
        return description;
    }

    protected LocalDateTime getTime() {
        return time;
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
     * Checks if the {@code Task}'s description contains a specified keyword.
     *
     * @param keyword The {@code String} to check.
     * @return true if the description contains the specified keyword.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
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
}
