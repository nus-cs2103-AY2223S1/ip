package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class that encapsulates the information of deadline task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param description task description.
     * @param by          the deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description task description
     * @param by          the deadline of the task
     * @param status      indicate whether the task has been done.
     */
    public Deadline(String description, LocalDateTime by, String status) {
        this(description, by);

        final String DONE = "1";

        if (status.equals(DONE)) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public LocalDateTime getDate() {
        return by;
    }

    @Override
    public String toString() {
        String dateString = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }
}
