package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in Duke.
 */
public class Deadline extends Task {
    private LocalDateTime time;

    /**
     * Creates a task that has an associated description and the time it is due.
     * @param task {@inheritDoc}
     * @param time The time of the task, in LocalDateTime format.
     * @see java.time.LocalDateTime
     */
    public Deadline(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "("
                + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }
}
