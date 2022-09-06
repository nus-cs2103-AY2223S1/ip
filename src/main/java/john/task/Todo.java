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
        String completionStatus = isCompleted ? "1" : "0";
        return String.format("T | %s | %s", completionStatus, description);
    }

    /**
     * A string representation of the to do.
     *
     * @return A string representation of the to do.
     */
    @Override
    public String toString() {
        String completionStatus = isCompleted ? "X" : " ";
        return String.format("[%s][%s] %s", this.getType(), completionStatus, description);
    }
}
