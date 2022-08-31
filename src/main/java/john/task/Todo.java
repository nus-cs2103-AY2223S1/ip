package john.task;

import java.time.LocalDate;

/**
 * Represents a To do task.
 */
public class Todo extends Task {
    /**
     * Constructor for a Event task.
     *
     * @param description The description of the to do.
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    protected String getType() {
        return "T";
    }

    /**
     * Returns false.
     *
     * @param date The date to equate to.
     * @return False.
     */
    @Override
    public boolean isEqualDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representing the storage format of the to do.
     *
     * @return A string representing the storage format of the to do.
     */
    @Override
    public String toStorageFormat() {
        return String.format("T | %d | %s", (this.isCompleted ? 1 : 0), this.description);
    }

    /**
     * A string representation of the to do.
     *
     * @return A string representation of the to do.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.getType(), (this.isCompleted ? "X" : " "), this.description);
    }
}
