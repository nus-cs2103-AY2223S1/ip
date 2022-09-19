package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with a due date.
 */
public class Deadline extends Task {
    private final LocalDate dateBy;

    /**
     * Constructs a deadline.
     *
     * @param title  Title of task.
     * @param done   If task is done.
     * @param dateBy Due date of task.
     */
    public Deadline(String title, boolean done, LocalDate dateBy) {
        super(title, done);
        this.dateBy = dateBy;
        assert !title.isEmpty();
        assert dateBy != null;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
