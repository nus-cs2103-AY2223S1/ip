package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates a task that need to be done by a certain time.
 *
 * @author Marcus Low
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
    private final LocalDateTime time;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param time Time the task needs to be done by.
     */
    public Deadline(String description, LocalDateTime time) {
        super(description, false);
        this.time = time;
    }

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param isDone Whether the task has been completed.
     * @param time Time the task needs to be done by.
     */
    public Deadline(String description, boolean isDone, LocalDateTime time) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[D][X] " + this.description + " (by: " + this.time.format(DF) + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + this.time.format(DF) + ")";
        }
    }

    @Override
    public String toStringText() {
        return "D | " + this.description + " | " + this.isDone + " | " + this.time.format(DF);
    }
}
