package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task happening at a certain time in duke.Duke.
 */
public class Event extends Task {
    private LocalDateTime time;

    /**
     * Creates a task that has an associated description and the time it happens.
     * @param task {@inheritDoc}
     * @param time The time of the task, in LocalDateTime format.
     * @see java.time.LocalDateTime
     */
    public Event(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "("
                + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }
}
