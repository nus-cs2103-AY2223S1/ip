package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class that encapsulates the information of deadline task.
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a Deadline object.
     * @param description task description.
     * @param by the deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object.
     * @param description task description
     * @param by the deadline of the task
     * @param status indicate whether the task has been done.
     */
    public Deadline(String description, LocalDate by, String status) {
        this(description, by);
        if (status.equals("1")) {
            super.markAsDone();
        }
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public LocalDate getDate() {
        return by;
    }

    @Override
    public String toString() {
        String dateString = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dateString + ")";
    }
}
